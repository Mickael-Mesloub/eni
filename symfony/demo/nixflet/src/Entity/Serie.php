<?php

namespace App\Entity;

use App\Repository\SerieRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: SerieRepository::class)]
#[ORM\HasLifecycleCallbacks]
#[ORM\UniqueConstraint(columns: ['name', 'first_air_date'])]
#[UniqueEntity(fields: ['name', 'firstAirDate'], message: 'La série {name} existe déjà')]
class Serie
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: 'La série doit avoir un titre !')]
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
    #[Assert\Type("\DateTimeInterface")]
    #[Assert\GreaterThan(propertyPath: 'firstAirDate', message: 'La date ne doit pas être anétrieure à la date de lancement')]
    #[Assert\When(
        expression: "this.getStatus() == 'returning'",
        constraints: [
            new Assert\Blank(message:"Une série en cours ne peut pas avoir de date de fin")
        ]
    )]
    #[Assert\When(
        expression: "this.getStatus() != 'returning'",
        constraints: [
            new Assert\Blank(message:"Vous devez renseigner une date de fin si la série est terminée ou abandonnée")
        ]
    )]
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

    /**
     * @var Collection<int, Season>
     */
    #[ORM\OneToMany(targetEntity: Season::class, mappedBy: 'serie', fetch: 'LAZY', orphanRemoval: true)]
    private Collection $seasons;

    public function __construct()
    {
        $this->seasons = new ArrayCollection();
    }

    #[ORM\PrePersist]
    public function onPersist(): void {
        $this->dateCreated = new \DateTime();
    }

    #[ORM\PreUpdate]
    public function onUpdate(): void {
        $this->dateModified = new \DateTime();
    }

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

    /**
     * @return Collection<int, Season>
     */
    public function getSeasons(): Collection
    {
        return $this->seasons;
    }

    public function addSeason(Season $season): static
    {
        if (!$this->seasons->contains($season)) {
            $this->seasons->add($season);
            $season->setSerie($this);
        }

        return $this;
    }

    public function removeSeason(Season $season): static
    {
        if ($this->seasons->removeElement($season)) {
            // set the owning side to null (unless already changed)
            if ($season->getSerie() === $this) {
                $season->setSerie(null);
            }
        }

        return $this;
    }
}
