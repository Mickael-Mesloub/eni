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
        return $this->render('wish/wishlist.html.twig', ['wishes' => $this->getWishes()]);
    }

    #[Route('/wish/{id}', name: 'app_wish_details', requirements: ['id' => '\d+'])]
    public function wishDetails(int $id): Response
    {
        $wishes = $this->getWishes();

        return $this->render('wish/wish-details.html.twig', [
            'id' => $id,
            'wish' => $wishes[$id]
        ]);
    }

    private function getWishes(): array
    {
        return [
            1 => 'Visiter la Nouvelle-Zélande',
            2 => 'Faire un tour d\'Asie',
            3 => 'Gagner au loto'
        ];
    }
}
