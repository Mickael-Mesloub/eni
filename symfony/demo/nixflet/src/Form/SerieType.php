<?php

namespace App\Form;

use App\Entity\Serie;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class SerieType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('name', TextType::class, [
                'label' => 'Nom de la série',
            ])
            ->add('overview', TextType::class, [
                'label' => 'Résumé',
            ])
            ->add('genres')

            ->add('status', ChoiceType::class, [
                'label' => 'Statut',
                'choices' => [
                    'En cours' => 'returning',
                    'Terminée' => 'ended',
                    'Abandonnée' => 'Cancelled',
                ],
                'placeholder' => '-- Choisir un statut --',
            ])
            ->add('vote')
            ->add('popularity')
            ->add('firstAirDate', DateType::class, [
                'widget' => 'choice',
                'format' => 'dd-MM-yyyy',
                'years' => range(1940,2050),
                'placeholder' => [
                    'day' => 'Jour',
                    'month' => 'Mois',
                    'year' => 'Année',
                ],
            ])
            ->add('lastAirDate' , DateType::class, [
                'widget' => 'choice',
                'required' => false,
                'format' => 'dd-MM-yyyy',
                'years' => range(1940,2050),
                'placeholder' => [
                    'day' => 'Jour',
                    'month' => 'Mois',
                    'year' => 'Année',
                ],
            ])
            ->add('backdrop')
            ->add('poster')
            ->add('submit' , SubmitType::class, [
                'label' => 'Enregistrer',
                'attr' => [
                    'class' => 'btn btn-primary',
                ]
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Serie::class,
        ]);
    }
}
