package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AddressRepositoryTest {

    AddressRepository addressRepository;

    @Autowired
    public AddressRepositoryTest(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Test
    public void saveAddressTest() {

        Address address = new Address("40, rue aaa", "48400", "aaa");
        address = addressRepository.createAddress(address);
        assertEquals(4, address.getId());
        assertEquals(4, addressRepository.getAllAddresses().size());
        addressRepository.deleteAddressById(address.getId());
    }

    @Test
    public void findAddressByIdTest() {
        Address address = addressRepository.getAddressById(1).get();

        assertNotNull(address);

    }

    @Test
    public void getAddressByCityAndZipcodeTest() {
        Address address = addressRepository.getAddressByStreetAndCityAndZipcode("Rue n°1", "44300", "Nantes").get();
        assertNotNull(address);
        assertEquals(1, address.getId());
    }
}
