<?php

namespace App\Controller;

use App\Entity\Wish;
use App\Form\WishType;
use App\Repository\WishRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
#[Route('/wishlist', name: 'app_wishlist')]
final class WishController extends AbstractController
{
    #[Route('/list', name: '_list')]
    public function liste(WishRepository $wishRepository): Response {
        $wishes = $wishRepository->findAll();

        return $this->render('wish/wishlist.html.twig', ['wishes' => $wishes]);
    }

    #[Route('/wish/{id}', name: '_wish_details', requirements: ['id' => '\d+'])]
    public function wishDetails(WishRepository $wishRepository, Wish $wish): Response
    {
        $wishes = $this->getWishes();

        return $this->render('wish/wish-details.html.twig', [
            'wish' => $wish
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

    #[Route('/wish/create', name: '_create_wish')]
    public function create(Request $request, EntityManagerInterface $em): Response {
        $wish = new Wish();
        $wishForm = $this->createForm(WishType::class, $wish);

        $wishForm->handleRequest($request);

        if($wishForm->isSubmitted() && $wishForm->isValid()) {
            $wish->setDateCreated(new \DateTime());
            $wish->setDateUpdated(new \DateTime());
            $em->persist($wish);
            $em->flush();

            $this->addFlash('success', 'GG ! Un nouveau souhait a été enregistrée');
            return $this->redirectToRoute('app_wishlist_list');
        }
        return $this->render('wish/create.html.twig', [
            'wish_form' => $wishForm->createView()
        ]);
    }
}
