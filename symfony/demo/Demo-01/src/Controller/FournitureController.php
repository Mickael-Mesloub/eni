<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

#[Route('/fourniture', name: 'app_fourniture')]
final class FournitureController extends AbstractController
{
    #[Route('/', name: '_list')]
    public function liste(): Response
    {
        // Redirection vers une route de l'application
        // return $this->redirectToRoute('app_home');

        // Redirection vers une route externe
        // return $this->redirect('https://google.com');

        // Retourner une response en JSON
        // return $this->json(['data' => 'plop', 'success' => true], Response::HTTP_OK);

        // Retourner une 401
        // throw $this->createAccessDeniedException("Vous n'avez pas les droits");

        return new Response('Page Liste des fournitures', Response::HTTP_OK);
    }

    #[Route('/edit/{id}', name: '_edit', requirements: ['id' => '\d+'])]
    public function edit(int $id): Response
    {
        return new Response("Page Édition de la fourniture n° $id", Response::HTTP_OK);
    }

    #[Route('/create', name: '_create')]
    public function create(): Response
    {
        return new Response('Page Création de la fourniture', Response::HTTP_OK);
    }
}
