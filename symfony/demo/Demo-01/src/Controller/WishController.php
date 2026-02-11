<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

final class WishController extends AbstractController
{
    #[Route('/wish', name: 'app_wishlist')]
    public function liste(): Response
    {
        return $this->render('wish/wishlist.html.twig');
    }

    #[Route('/wish/{id}', name: 'app_wish_details', requirements: ['id' => '\d+'])]
    public function wishDetails(int $id): Response
    {
        return $this->render('wish/wish-details.html.twig', ['id' => $id]);
    }
}
