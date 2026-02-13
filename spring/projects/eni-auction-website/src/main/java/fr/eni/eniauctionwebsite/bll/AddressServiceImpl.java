package fr.eni.eniauctionwebsite.bll;

import fr.eni.eniauctionwebsite.bo.Address;
import fr.eni.eniauctionwebsite.dal.AddressRepository;
import fr.eni.eniauctionwebsite.exception.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    AddressRepository addressRepository;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddresses();
    }

    @Override
    public Address getAddressById(int id) {
        return addressRepository.getAddressById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    public Address getAddressByStreetAndCityAndZipcode(String street, String city, String zipcode) {
        return addressRepository.getAddressByStreetAndCityAndZipcode(street, city, zipcode)
                .orElseThrow(() -> new AddressNotFoundException(street, city, zipcode));
    }

    @Override
    public void deleteAddressById(int id) {
        addressRepository.deleteAddressById(id);
    }
}
