<?php

namespace App\Entity;

use App\Repository\WishRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: WishRepository::class)]
class Wish
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 250)]
    #[Assert\NotBlank(message: 'Le souhait doit avoir un titre !')]
    #[Assert\Length(
        min: 3,
        max: 250,
        minMessage: "Le titre du souhait doit faire au moins {{ limit }} caractères",
        maxMessage: "Le titre du souhait doit faire au maximum {{ limit }} caractères"
    )]
    private ?string $title = null;

    #[ORM\Column(type: Types::TEXT, nullable: true)]
    #[Assert\NotBlank(message: 'Le souhait doit avoir une description !')]
    #[Assert\Length(
        min: 3,
        max: 1000,
        minMessage: "La description du souhait doit faire au moins {{ limit }} caractères",
        maxMessage: "La description du souhait doit faire au maximum {{ limit }} caractères"
    )]
    private ?string $description = null;

    #[ORM\Column(length: 50, nullable: true)]
    #[Assert\NotBlank(message: 'Le souhait doit avoir un auteur !')]
    #[Assert\Email(
        message: 'L\'auteur doit être un email',
    )]
    #[Assert\Length(
        min: 5,
        max: 50,
        minMessage: "L'auteur du souhait doit faire au moins {{ limit }} caractères",
        maxMessage: "L'auteur du souhait doit faire au maximum {{ limit }} caractères"
    )]
    private ?string $author = null;

    #[ORM\Column]
    private ?bool $isPublished = null;

    #[ORM\Column]
    private ?\DateTime $dateCreated = null;

    #[ORM\Column]
    private ?\DateTime $dateUpdated = null;

    #[ORM\ManyToOne(inversedBy: 'Wish')]
    private ?Category $category = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): static
    {
        $this->title = $title;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): static
    {
        $this->description = $description;

        return $this;
    }

    public function getAuthor(): ?string
    {
        return $this->author;
    }

    public function setAuthor(?string $author): static
    {
        $this->author = $author;

        return $this;
    }

    public function isPublished(): ?bool
    {
        return $this->isPublished;
    }

    public function setIsPublished(bool $isPublished): static
    {
        $this->isPublished = $isPublished;

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

    public function getDateUpdated(): ?\DateTime
    {
        return $this->dateUpdated;
    }

    public function setDateUpdated(\DateTime $dateUpdated): static
    {
        $this->dateUpdated = $dateUpdated;

        return $this;
    }

    public function getCategory(): ?Category
    {
        return $this->category;
    }

    public function setCategory(?Category $category): static
    {
        $this->category = $category;

        return $this;
    }
}
