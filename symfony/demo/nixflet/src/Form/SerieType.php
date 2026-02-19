<?php

namespace App\Form;

use App\DataTransformer\SlashTransformer;
use App\Entity\Serie;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\CallbackTransformer;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\File;

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
            ->add('genres', ChoiceType::class, [
                    'label' => 'Genre',
                    'choices' => [
                        'War' => 'War',
                        'Politics' => 'Politics',
                        'Drama' => 'Drama',
                        'Thriller' => 'Thriller',
                        'Horror' => 'Horror',
                        'Comedy' => 'Comedy',
                        'Romance' => 'Romance',
                        'Sci-Fi' => 'Sci-Fi',
                        'Crime' => 'Crime',
                        'Aventure' => 'Aventure',
                        'Mystery' => 'Mystery',
                        'Action' => 'Action',
                        'Fantasy' => 'Fantasy',
                        'History' => 'History',
                        'Kids' => 'Kids',
                        'Family' => 'Family',
                        'Soap' => 'Soap',
                    ],
                'multiple' => true,
                ])

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
            ->add('posterFile', FileType::class, [
                'label' => 'Poster',
                'mapped' => false,
                'required' => false,
                'constraints' => [
                    new File([
                        'maxSize' => '1024k',
                        'maxSizeMessage' => 'Votre fichier est trop lourd ! Max: 1MO',
                        'mimeTypes' => [
                            'image/jpeg',
                            'image/jpg',
                            'image/png',
                        ],
                        'mimeTypesMessage' => 'Format de fichier invalide ! Formats acceptés: jpg, jpeg, png',
                    ])
                ]
            ])
            ->add('submit' , SubmitType::class, [
                'label' => 'Enregistrer',
                'attr' => [
                    'class' => 'btn btn-primary',
                ]
            ])
        ;
        $builder->get('genres')->addModelTransformer(new SlashTransformer());
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Serie::class,
        ]);
    }
}
