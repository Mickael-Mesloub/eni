package fr.eni.eniauctionwebsite.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class UpdateUserDTO {
    @NotBlank(message = "Le champ \"Pseudo\" est obligatoire")
    @Size(min = 2, max = 30, message = "Le pseudo doit faire entre {min} et {max} caractères")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String username;

    @NotBlank(message = "Le champ \"Nom de famille\" est obligatoire")
    @Size(min = 2, max = 30, message = "Le nom de famille doit faire entre {min} et {max} caractères")
    private String lastname;

    @NotBlank(message = "Le champ \"Prénom\" est obligatoire")
    @Size(min = 2, max = 30, message = "Le prénom doit faire entre {min} et {max} caractères")
    private String firstname;

    @NotBlank(message = "Le champ \"Email\" est obligatoire")
    @Email
    @Size(min = 5, max = 50, message = "L'email doit faire entre {min} et {max} caractères")
    private String email;

    @NotBlank(message = "Le champ \"Téléphone\" est obligatoire")
    @Pattern(regexp = "^0[1-9]\\d{8}$", message = "Format attendu : 0XXXXXXXXX")
    private String phone;

    @NotBlank(message = "Le champ \"Rue\" est obligatoire")
    @Size(min = 2, max = 150, message = "La rue doit faire entre {min} et {max} caractères")
    private String street;

    @NotBlank(message = "Le champ \"Code postal\" est obligatoire")
    @Pattern(regexp = "^(?:0[1-9]|[1-8]\\d|9[0-8])\\d{3}$", message = "Format attendu : XXXXX")
    private String zipcode;

    @NotBlank(message = "Le champ \"Ville\" est obligatoire")
    @Size(min = 1, max = 30, message = "La ville doit faire entre {min} et {max} caractères")
    private String city;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String username, String lastname, String firstname, String email, String phone, String street, String zipcode, String city) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    @Override
    public String toString() {
        return "UpdateUserDTO{" +
                "username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UpdateUserDTO that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(street, that.street) && Objects.equals(zipcode, that.zipcode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, lastname, firstname, email, phone, street, zipcode, city);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
