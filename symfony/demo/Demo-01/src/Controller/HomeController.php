<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

final class HomeController extends AbstractController
{
    #[Route('/', name: 'app_home')]
    public function index(): Response
    {
        return $this->render('home/index.html.twig');
    }

    // Dynamic route
    #[Route('/truc/{id}', name: 'app_truc', requirements: ['id' => '\d+'], methods: ['GET'])]
    public function truc(int $id): Response
    {

        return $this->render('truc/truc.html.twig', ['id' => $id]);
    }

    // Route avec param page
    #[Route('/machin/{page}', name: 'app_machin', requirements: ['page' => '\d+'], methods: ['GET'])]
    public function machinPage(int $page = 1): Response
    {
        return $this->render('machin/machin.html.twig', ['page' => $page]);
    }

    #[Route('/about-us', name: 'app_about_us')]
    public function aboutUs(): Response
    {
        return $this->render('about-us/about-us.html.twig');
    }
}

