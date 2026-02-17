<?php

namespace App\Repository;

use App\Entity\Serie;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Serie>
 */
class SerieRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Serie::class);
    }

    // Fonction custom pour chercher en base des séries avec des critères/filtres
    public function findSeriesCustom(int $offset, int $limit, string $status, \DateTime $date, ?float $vote = null): array
    {
        $q = $this->createQueryBuilder('s')
            ->orderBy('s.popularity', 'DESC')
            ->andWhere('s.status = :status OR s.firstAirDate <= :date')
            ->setParameter('status', 'En cours')
            ->setParameter('date', $date);

        if ($vote) {
            $q->orWhere('s.vote >= :vote')
                ->setParameter('vote', $vote);
        }

        $q2 = clone $q;
        $q2->select('COUNT(s.id)');

        return [
            $q2->getQuery()->getSingleScalarResult(),
            $q->setFirstResult($offset)
            ->setMaxResults($limit)
            ->getQuery()
            ->getResult()];
    }

    public function findSeriesDQL(): array {
        $dql = "SELECT s FROM App\Entity\Serie s
            WHERE (s.firstAirDate <= :date OR s.status = :status)
            AND s.name LIKE :partial
            ORDER BY s.popularity DESC";

        return $this->getEntityManager()
            ->createQuery($dql)
            ->setParameter(['date', new \DateTime('1990-01-01')])
            ->setParameter(['status' => "ended"])
            ->setParameter(['partial' => "%a%"])
            ->setMaxResults(10)
            ->setFirstResult(0)
            ->execute();
    }

    public function getSerieWithSeasons(int $id): Serie {
        return $this->createQueryBuilder('serie')
            ->addSelect('seasons')
            ->leftJoin('serie.seasons', 'seasons')
            ->andWhere('serie.id = :id')
            ->setParameter('id', $id)
            ->getQuery()
            ->getOneOrNullResult();
    }


    //    /**
    //     * @return Serie[] Returns an array of Serie objects
    //     */
    //    public function findByExampleField($value): array
    //    {
    //        return $this->createQueryBuilder('s')
    //            ->andWhere('s.exampleField = :val')
    //            ->setParameter('val', $value)
    //            ->orderBy('s.id', 'ASC')
    //            ->setMaxResults(10)
    //            ->getQuery()
    //            ->getResult()
    //        ;
    //    }

    //    public function findOneBySomeField($value): ?Serie
    //    {
    //        return $this->createQueryBuilder('s')
    //            ->andWhere('s.exampleField = :val')
    //            ->setParameter('val', $value)
    //            ->getQuery()
    //            ->getOneOrNullResult()
    //        ;
    //    }
}
