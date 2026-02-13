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
            ->setFirstResult($offset)
            ->setMaxResults($limit)
            ->andWhere('s.status = :status OR s.firstAirDate <= :date')
            ->setParameter('status', 'En cours')
            ->setParameter('date', $date);

        if ($vote) {
            $q->orWhere('s.vote >= :vote')
                ->setParameter('vote', $vote);
        }

        return $q->getQuery()
            ->getResult();
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
