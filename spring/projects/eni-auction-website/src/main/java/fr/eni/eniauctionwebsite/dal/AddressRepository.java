package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.bo.User;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> getAllAddresses();
    Address createAddress(Address address);
    Optional<Address> getAddressById(int id);
    Optional<Address> getAddressByStreetAndCityAndZipcode(String street, String zipcode, String city);
    void deleteAddressById(int id);
}
