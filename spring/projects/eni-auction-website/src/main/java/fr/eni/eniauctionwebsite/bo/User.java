package fr.eni.eniauctionwebsite.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Address address;
    private String password;
    private int creditPoints;
    private boolean isAdmin;


    private List<Bid> bids = new ArrayList<>();
    private List<Item> itemSales = new ArrayList<>();


    // CONSTRUCTORS
    public User() {
    }
    public User(String username, String firstname, String lastname, String email, String phone, Address address, String password, int creditPoints, boolean isAdmin, List<Bid> bids, List<Item> itemSales) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.creditPoints = creditPoints;
        this.isAdmin = isAdmin;
        this.bids = bids;
        this.itemSales = itemSales;
    }
    public User(int id, String username, String firstname, String lastname, String email, String phone, Address address, String password, int creditPoints, boolean isAdmin, List<Bid> bids, List<Item> itemSales) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.creditPoints = creditPoints;
        this.isAdmin = isAdmin;
        this.bids = bids;
        this.itemSales = itemSales;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", password='" + password + '\'' +
                ", creditPoints=" + creditPoints +
                ", isAdmin=" + isAdmin +
                ", bids=" + bids +
                ", itemSales=" + itemSales +
                '}';
    }

    // EQUALS / HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && creditPoints == user.creditPoints && isAdmin == user.isAdmin && Objects.equals(username, user.username) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(address, user.address) && Objects.equals(password, user.password) && Objects.equals(bids, user.bids) && Objects.equals(itemSales, user.itemSales);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstname, lastname, email, phone, address, password, creditPoints, isAdmin, bids, itemSales);
    }

    // GETTERS / SETTERS
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getCreditPoints() {
        return creditPoints;
    }
    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public List<Bid> getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    public List<Item> getItemSales() {
        return itemSales;
    }
    public void setItemSales(List<Item> itemSales) {
        this.itemSales = itemSales;
    }
}
