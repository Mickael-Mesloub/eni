package fr.eni.eniauctionwebsite.dal;

import fr.eni.eniauctionwebsite.bo.ItemCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemCategoryRepositoryImplTest {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemCategoryRepository itemCategoryRepository;

    @Test
    @DisplayName("Test findAllItemCategories cas plusieurs Catégories existent")
    public void findAllItemCategories() {
        //Act
        List<ItemCategory> itemCategories = itemCategoryRepository.findAllItemCategories();
        //Assert
        assertNotNull(itemCategories);
        Assertions.assertEquals(4, itemCategories.size());
    }


    @Test
    @DisplayName("Test findItemCategoryById cas l'id existe")
    public void findItemCategoryByIdExist() {
        //Arrange
        int id = 3;

        //Act
        Optional<ItemCategory> itemCategory = itemCategoryRepository.findItemCategoryById(id);

        //Assert
        assertNotNull(itemCategory);
        assertEquals("Vêtements", itemCategory.get().getDescription());
    }

}
