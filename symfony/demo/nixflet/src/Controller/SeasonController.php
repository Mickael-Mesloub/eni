<?php

namespace App\Controller;

use App\Entity\Season;
use App\Entity\Serie;
use App\Form\SeasonType;
use App\Form\SerieType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/season', name: 'app_season')]
final class SeasonController extends AbstractController
{
    #[Route('/create', name: '_create')]
    public function create(Request $request, EntityManagerInterface $em): Response
    {
        $season = new Season();
        $seasonForm = $this->createForm(SeasonType::class, $season);
        $seasonForm->handleRequest($request);
        if ($seasonForm->isSubmitted() && $seasonForm->isValid()) {
            $em->persist($season);
            $em->flush();

            $this->addFlash('success', 'Une nouvelle saison a été créée !');
            return $this->redirectToRoute('app_serie_detail', ['id' => $season->getSerie()->getId()]);
        }

        return $this->render('season/edit.html.twig', [
            'season_form' => $seasonForm,
        ]);
    }

    #[Route('/update/{id}', name: '_update', requirements: ['id' => '\d+'])]
    public function update(Request $request, EntityManagerInterface $em, Season $season): Response
    {
        $seasonForm = $this->createForm(SeasonType::class, $season);
        $seasonForm->handleRequest($request);
        if ($seasonForm->isSubmitted() && $seasonForm->isValid()) {
            $em->flush();
            $this->addFlash('success', "La saison {$season->getId()} a été modifiée");
            return $this->redirectToRoute('app_season_detail', ['id' => $season->getId()]);
        }

        return $this->render('season/edit.html.twig', [
            'season_form' => $seasonForm,
            'season' => $season,
        ]);
    }
}
