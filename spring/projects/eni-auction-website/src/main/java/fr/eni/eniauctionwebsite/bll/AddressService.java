package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    Address getAddressById(int id);
    Address getAddressByStreetAndCityAndZipcode(String street, String city, String zipcode);
    void deleteAddressById(int id);
}
