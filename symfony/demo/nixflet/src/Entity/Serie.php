<?php

namespace App\Entity;

use App\Repository\SerieRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: SerieRepository::class)]
class Serie
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: 'La série doit avoir un titre !!!')]
    #[Assert\Length(
        min: 3,
        max: 15,
        minMessage: "La série doit avoir au moins {{ limit }} caractères",
        maxMessage: "La série doit avoir au maximum {{ limit }} caractères"
    )]
    private ?string $name = null;

    #[ORM\Column(type: Types::TEXT, nullable: true)]
    private ?string $overview = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: 'Vous devez choisir un statut')]
    #[Assert\Choice( choices: ['returning', 'ended', 'Cancelled'], message: 'Choix invalide')]
    private ?string $status = null;

    #[ORM\Column(nullable: true)]
    #[Assert\Range(
        notInRangeMessage: 'La note doit être comprise entre {{ min }} et {{ max }}',
        min: 0,
        max: 10
    )]
    private ?float $vote = null;

    #[ORM\Column(nullable: true)]
    private ?float $popularity = null;

    #[ORM\Column(length: 255)]
    private ?string $genres = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\LessThan('today', message: 'Cette date doit être antérieure à aujourd\'hui')]
    private ?\DateTime $firstAirDate = null;

    #[ORM\Column(type: Types::DATE_MUTABLE, nullable: true)]
    #[Assert\GreaterThan(propertyPath: 'firstAirDate', message: 'La date ne doit pas être anétrieure à la date de lancement')]
    private ?\DateTime $lastAirDate = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $backdrop = null;

    #[ORM\Column(length: 255, nullable: true)]
    private ?string $poster = null;

    #[ORM\Column(nullable: true)]
    private ?int $tmdbId = null;

    #[ORM\Column]
    private ?\DateTime $dateCreated = null;

    #[ORM\Column(nullable: true)]
    private ?\DateTime $dateModified = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): static
    {
        $this->name = $name;

        return $this;
    }

    public function getOverview(): ?string
    {
        return $this->overview;
    }

    public function setOverview(?string $overview): static
    {
        $this->overview = $overview;

        return $this;
    }

    public function getStatus(): ?string
    {
        return $this->status;
    }

    public function setStatus(string $status): static
    {
        $this->status = $status;

        return $this;
    }

    public function getVote(): ?float
    {
        return $this->vote;
    }

    public function setVote(?float $vote): static
    {
        $this->vote = $vote;

        return $this;
    }

    public function getPopularity(): ?float
    {
        return $this->popularity;
    }

    public function setPopularity(?float $popularity): static
    {
        $this->popularity = $popularity;

        return $this;
    }

    public function getGenres(): ?string
    {
        return $this->genres;
    }

    public function setGenres(string $genres): static
    {
        $this->genres = $genres;

        return $this;
    }

    public function getFirstAirDate(): ?\DateTime
    {
        return $this->firstAirDate;
    }

    public function setFirstAirDate(\DateTime $firstAirDate): static
    {
        $this->firstAirDate = $firstAirDate;

        return $this;
    }

    public function getLastAirDate(): ?\DateTime
    {
        return $this->lastAirDate;
    }

    public function setLastAirDate(?\DateTime $lastAirDate): static
    {
        $this->lastAirDate = $lastAirDate;

        return $this;
    }

    public function getBackdrop(): ?string
    {
        return $this->backdrop;
    }

    public function setBackdrop(?string $backdrop): static
    {
        $this->backdrop = $backdrop;

        return $this;
    }

    public function getPoster(): ?string
    {
        return $this->poster;
    }

    public function setPoster(?string $poster): static
    {
        $this->poster = $poster;

        return $this;
    }

    public function getTmdbId(): ?int
    {
        return $this->tmdbId;
    }

    public function setTmdbId(?int $tmdbId): static
    {
        $this->tmdbId = $tmdbId;

        return $this;
    }

    public function getDateCreated(): ?\DateTime
    {
        return $this->dateCreated;
    }

    public function setDateCreated(\DateTime $dateCreated): static
    {
        $this->dateCreated = $dateCreated;

        return $this;
    }

    public function getDateModified(): ?\DateTime
    {
        return $this->dateModified;
    }

    public function setDateModified(?\DateTime $dateModified): static
    {
        $this->dateModified = $dateModified;

        return $this;
    }
}
