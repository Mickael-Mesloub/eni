package fr.eni.eniauctionwebsite.controller.dto.item;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ItemDTO {

    @NotBlank(message = "Le champ \"Nom de l\'article\" est obligatoire")
    @Size(min = 2, max = 30, message = "Le nom de l\'article doit faire entre {min} et {max} caractères")
    private String name;

    @NotBlank(message = "Le champ \"Description\"  est obligatoire.")
    @Size(min = 2, max = 300, message = "La description doit faire entre {min} et {max} caractères.")
    private String description;

    private Integer categoryId;

    @Min(value = 1, message = "Le prix de vente doit être supérieur à 0")
    private int startingPrice;

    private int currentPrice;

    @FutureOrPresent(message = "La date de début de la vente ne peut pas être inférieure à la date du jour.")
    private LocalDateTime auctionStartDate;

    @Future(message = "La date de fin de l\'enchère ne peut pas être inférieure ou égale à la date du jour")
    private LocalDateTime auctionEndDate;

    private String street;
    private String zipcode;
    private String city;

    private MultipartFile imageFile;
    private String imagePath;


    // ================= GETTERS / SETTERS =================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public LocalDateTime getAuctionStartDate() {
        return auctionStartDate;
    }

    public void setAuctionStartDate(LocalDateTime auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }

    public LocalDateTime getAuctionEndDate() {
        return auctionEndDate;
    }

    public void setAuctionEndDate(LocalDateTime auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile image) {
        this.imageFile = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
