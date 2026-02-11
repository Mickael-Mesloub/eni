<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

final class UdonController extends AbstractController
{
    #[Route('/udon', name: 'app_udon')]
    public function index(): Response {
        $name = "Udon";
        return $this->render('udon/udon.html.twig', [
            'name' => $name,
        ]);
    }
}
