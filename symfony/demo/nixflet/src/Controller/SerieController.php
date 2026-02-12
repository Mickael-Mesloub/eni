<?php

namespace App\Controller;

use App\Entity\Serie;
use App\Repository\SerieRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/serie', name: 'app_serie')]
final class SerieController extends AbstractController
{
    #[Route('/test', name: '_test')]
    public function test(EntityManagerInterface $em): Response
    {
        $serie = new Serie();
        $serie->setName('Derrick')
            ->setOverview('Encore une enquête pour l\'inspecteur le plus malinois !')
            ->setStatus('Ended')
            ->setGenres('policier')
            ->setFirstAirDate(new \DateTime('1974-10-20'))
            ->setLastAirDate(new \DateTime('1998-10-16'))
            ->setDateCreated(new \DateTime());

        $em->persist($serie);
        $em->flush();

        return new Response('Derrick est à la maison !');
    }

    #[Route('/liste/{page}', name: '_liste', requirements: ['page' => '\d+'], methods: ['GET'])]
    public function liste(SerieRepository $serieRepository, ParameterBagInterface $parameterBag, int $page = 1): Response
    {
        // On récupère le nb_limit_series défini dans config/services.yaml
        $limit = $parameterBag->get('nb_limit_series');
        $page = max($page, 1);
        $offset = ($page - 1) * 10;

        $criterias = [
            'status' => "returning",
            'genres' => "horreur",
        ];

        $nbTotal = $serieRepository->count($criterias);
        $nbPagesMax = ceil($nbTotal / $limit);

        if($page > $nbPagesMax) {
            throw $this->createNotFoundException("La page $page n'existe pas.");
        }

//        $series = $serieRepository->findAll();
        $series = $serieRepository->findBy($criterias,
        [
            'firstAirDate' => 'DESC',
            'dateCreated' => 'DESC',

        ], $limit, $offset);

        return $this->render('serie/liste.html.twig', [
            'series' => $series,
            'page' => $page,
            'nb_pages_max' => $nbPagesMax,
        ]);
    }
}
