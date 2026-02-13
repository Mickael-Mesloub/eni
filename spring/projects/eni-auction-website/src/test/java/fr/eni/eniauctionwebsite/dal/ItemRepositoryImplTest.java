package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.Item;
import fr.eni.eniauctionwebsite.bo.ItemCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ItemRepositoryImplTest {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepository itemRepository;

    @Test
    @DisplayName("Test findAllItems cas plusieurs Items existent")
    public void findAllItems() {
        //Act
        List<Item> items = itemRepository.findAllItems();
        //Assert
        assertNotNull(items);
        Assertions.assertEquals(3, items.size());
    }


    @Test
    @DisplayName("Test findItemById cas l'id existe")
    public void findItemByIdExist() {
        //Arrange
        int id = 2;

        //Act
        Optional<Item> item = itemRepository.getItemById(id);

        //Assert
        assertNotNull(item);
        assertEquals("Grosses lunettes", item.get().getDescription());
    }


}
