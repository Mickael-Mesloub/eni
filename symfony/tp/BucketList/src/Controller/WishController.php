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
    public function wishDetails(WishRepository $wishRepository, int $id): Response
    {
        $wish = $wishRepository->find($id);

        return $this->render('wish/wish-details.html.twig', [
            'wish' => $wish
        ]);
    }

    #[Route('/wish/create', name: '_create_wish')]
    public function create(Request $request, EntityManagerInterface $em): Response {
        $user = $this->getUser();
        $wish = new Wish();

        $wish->setAuthor($user->getUserIdentifier());
        $wishForm = $this->createForm(WishType::class, $wish);

        $wishForm->handleRequest($request);

        if($wishForm->isSubmitted() && $wishForm->isValid()) {
            $wish->setDateCreated(new \DateTime());
            $em->persist($wish);
            $em->flush();

            $this->addFlash('success', 'GG ! Un nouveau souhait a été enregistré !');
            return $this->redirectToRoute('app_wishlist_list');
        }

        return $this->render('wish/edit.html.twig', [
            'wish_form' => $wishForm->createView()
        ]);
    }

    #[Route('/update/{id}', name: '_update_wish', requirements: ['id' => '\d+'])]
    public function update(Request $request, EntityManagerInterface $em, Wish $wish): Response
    {
        $user = $this->getUser();
        $wish->setAuthor($user->getUserIdentifier());
        $wishForm = $this->createForm(WishType::class, $wish);
        $wishForm->handleRequest($request);
        if ($wishForm->isSubmitted() && $wishForm->isValid()) {
            $em->flush();
            $this->addFlash('success', "Le souhait {$wish->getTitle()} a été modifié");
            return $this->redirectToRoute('app_wishlist_wish_detail', ['id' => $wish->getId()]);
        }

        return $this->render('wish/edit.html.twig', [
            'wish_form' => $wishForm,
            'wish' => $wish,
        ]);
    }

    #[Route('/delete/{id}', name: '_delete_wish', requirements: ['id' => '\d+'])]
    public function delete(Wish $wish, EntityManagerInterface $em, Request $request): Response
    {
        $token = $request->query->get('token');

        if ($this->isCsrfTokenValid('wish_delete' . $wish->getId(), $token)) {
            $em->remove($wish);
            $em->flush();

            $this->addFlash('success', 'Le souhait a été supprimé');
            return $this->redirectToRoute('app_wishlist_list');
        }

        $this->addFlash('danger', 'Cette action est impossible!');
        return $this->redirectToRoute('app_wishlist_wish_detail', ['id' => $wish->getId()]);
    }
}
