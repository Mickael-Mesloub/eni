<?php

namespace App\DataFixtures;

use App\Entity\Serie;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use Faker\Factory;

class SerieFixtures extends Fixture
{
    public function load(ObjectManager $manager): void
    {
        $faker = Factory::create('fr_FR');

        for($i = 0; $i < 1000; $i++) {
            $serie = new Serie();
            $serie->setName($faker->realText(30))
                ->setOverview($faker->realText(150))
                ->setStatus($faker->randomElement(['returning', 'ended', 'Canceled']))
                ->setVote($faker->randomFloat(2, 1, 10))
                ->setGenres($faker->randomElement(['Thriller', 'Horreur', 'Policier allemand', 'Drama', 'Western', 'SF', 'Comédie']))
                ->setPopularity($faker->randomFloat(2, 200, 1000))
                ->setFirstAirDate($faker->dateTimeBetween('-50 year', '- 1 month'))
                ->setDateCreated($faker->dateTimeBetween($serie->getFirstAirDate()));

            if($serie->getStatus() !== 'returning') {
                $serie->setLastAirDate($faker->dateTimeBetween($serie->getFirstAirDate(), '-3 days'));
            }

            $manager->persist($serie);
        }

        $manager->flush();
    }
}
