<?php

namespace App\Controller;

use App\Entity\Serie;
use App\Repository\SerieRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
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

    #[Route('/liste', name: '_liste', methods: ['GET'])]
    public function liste(SerieRepository $serieRepository): Response
    {
        $series = $serieRepository->findAll();

        return $this->render('serie/liste.html.twig', ['series' => $series]);
    }
}
