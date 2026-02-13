package fr.eni.eniauctionwebsite.bo;

import java.util.Objects;

public class Address {
    private int id;
    private String street;
    private String zipcode;
    private String city;

    // CONSTRUCTORS
    public Address() {}
    public Address(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
    public Address(int id, String street, String zipcode, String city) {
        this.id = id;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    // TOSTRING


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    // EQUALS HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode) && Objects.equals(city, address.city);
    }
    @Override
    public int hashCode() {
        return Objects.hash(street, zipcode, city);
    }

    // GETTERS / SETTERS
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
