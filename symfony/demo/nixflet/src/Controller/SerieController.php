<?php

namespace App\Controller;

use App\Entity\Serie;
use App\Form\SerieType;
use App\Repository\SerieRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Component\Security\Http\Attribute\IsGranted;
use Symfony\Component\String\Slugger\SluggerInterface;

#[Route('/serie', name: 'app_serie')]
#[IsGranted("ROLE_USER")]
final class SerieController extends AbstractController
{
    #[Route('/liste/{page}', name: '_liste_find_by', requirements: ['page' => '\d+'], methods: ['GET'])]
    public function listeFindBy(SerieRepository $serieRepository, ParameterBagInterface $parameterBag, int $page = 1): Response
    {
        // On récupère le nb_limit_series défini dans config/services.yaml
        $limit = $parameterBag->get('nb_limit_series');
        $page = max($page, 1);
        $offset = ($page - 1) * 10;

        $criterias = [
            'status' => "returning",
//            'genres' => "horreur",
        ];

        $nbTotal = $serieRepository->count($criterias);
        $nbPagesMax = ceil($nbTotal / $limit);

        if($page > $nbPagesMax) {
            throw $this->createNotFoundException("La page $page n'existe pas.");
        }

        $series = $serieRepository->findAll();
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

    #[Route('/liste/find_custom/{page}', name: '_liste_find_custom', requirements: ['page' => '\d+'], methods: ['GET'])]
    public function listeFindCustom(SerieRepository $serieRepository, ParameterBagInterface $parameterBag, int $page = 1): Response
    {
        // On récupère le nb_limit_series défini dans config/services.yaml
        $limit = $parameterBag->get('nb_limit_series');
        $page = max($page, 1);
        $offset = ($page - 1) * 10;

        $criterias = [
            'status' => "returning",
//            'genres' => "horreur",
        ];

        $nbTotal = $serieRepository->count($criterias);
        $nbPagesMax = ceil($nbTotal / $limit);

        if($page > $nbPagesMax) {
            throw $this->createNotFoundException("La page $page n'existe pas.");
        }

//        $series = $serieRepository->findAll();
//        $series = $serieRepository->findBy($criterias,
//        [
//            'firstAirDate' => 'DESC',
//            'dateCreated' => 'DESC',
//
//        ], $limit, $offset);

        $series = $serieRepository->findSeriesCustom($offset, $limit, 'returning', new \DateTime('1990-01-01'), 8);

        return $this->render('serie/liste.html.twig', [
            'series' => $series,
            'page' => $page,
            'nb_pages_max' => $nbPagesMax,
        ]);
    }

    #[Route('/detail/{id}', name: '_detail', requirements: ['id' => '\d+'], methods: ['GET'])]
    public function detail(
        SerieRepository $serieRepository,
         int $id,
    ): Response {
        // On peut directement passer la série en param pour se passer du find.
        // Bon à savoir : Le find() ne fonctionne qu'avec une clé primaire !
        // Pour chercher avec autre chose, on va utiliser findOneBy()
//        $serie = $serieRepository->find($id);
//
//        if(!$serie) {
//            throw $this->createNotFoundException("Le serie $id n'existe pas.");
//        }

        $serie = $serieRepository->getSerieWithSeasons($id);

        return $this->render('serie/detail.html.twig', [
            'serie' => $serie
        ]);
    }

    #[Route('/create', name: '_create')]
    #[IsGranted("ROLE_CONTRIB")]
    public function create(Request $request, EntityManagerInterface $em, SluggerInterface $slugger): Response
    {
        $serie = new Serie();
        $serieForm = $this->createForm(SerieType::class, $serie);
        $serieForm->handleRequest($request);
        if ($serieForm->isSubmitted() && $serieForm->isValid()) {
            $file = $serieForm->get('posterFile')->getData();

            if($file instanceof UploadedFile) {
                $newFileName =  sprintf('%s-%s.%s', $slugger->slug($serie->getName()), uniqid(), $file->guessExtension());
                $dir = $this->getParameter('poster_path');
                $file->move($dir, $newFileName);
                $serie->setPoster($newFileName);
            }

            $em->persist($serie);
            $em->flush();

            $this->addFlash('success', 'Une nouvelle série a été enregistrée');
            return $this->redirectToRoute('app_serie_detail', ['id' => $serie->getId()]);
        }

        return $this->render('serie/edit.html.twig', [
            'serie_form' => $serieForm,
        ]);
    }

    #[Route('/update/{id}', name: '_update', requirements: ['id' => '\d+'])]
    #[IsGranted("ROLE_CONTRIB")]
    public function update(Request $request, EntityManagerInterface $em, Serie $serie): Response
    {
        $serieForm = $this->createForm(SerieType::class, $serie);
        $serieForm->handleRequest($request);
        if ($serieForm->isSubmitted() && $serieForm->isValid()) {
            $em->flush();
            $this->addFlash('success', "La série {$serie->getName()} a été modifiée");
            return $this->redirectToRoute('app_serie_detail', ['id' => $serie->getId()]);
        }

        return $this->render('serie/edit.html.twig', [
            'serie_form' => $serieForm,
            'serie' => $serie,
        ]);
    }

    #[Route('/delete/{id}', name: '_delete', requirements: ['id' => '\d+'])]
    #[IsGranted("ROLE_ADMIN")]
    public function delete(Serie $serie, EntityManagerInterface $em, Request $request): Response
    {
        $token = $request->query->get('token');

        if ($this->isCsrfTokenValid('serie_delete' . $serie->getId(), $token)) {
            $em->remove($serie);
            $em->flush();

            $this->addFlash('success', 'Une série a été supprimée');
            return $this->redirectToRoute('app_serie_liste');
        }

        $this->addFlash('danger', 'Cette action est impossible!');
        return $this->redirectToRoute('app_serie_detail', ['id' => $serie->getId()]);
    }
}
