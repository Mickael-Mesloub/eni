package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exception.GenreNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GenreRepositoryImplTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each test");
        jdbcTemplate.update("delete from genres");
        jdbcTemplate.update("insert into genres(id, titre) values(1, 'Animation')");
        jdbcTemplate.update("insert into genres(id, titre) values(2, 'Science-fiction')");
        jdbcTemplate.update("insert into genres(id, titre) values(3, 'Documentaire')");
        jdbcTemplate.update("insert into genres(id, titre) values(4, 'Action')");
        jdbcTemplate.update("insert into genres(id, titre) values(5, N'Comédie')");
        jdbcTemplate.update("insert into genres(id, titre) values(6, 'Drame')");
    }

    @Test
    @DisplayName("Test findAllGenres - Cas droit")
    void testFindAllGenres() {
        // Arrange
        List<Genre> genres =  genreRepository.findAllGenres();

        // Act + Assert
        assertNotNull(genres);
        assertFalse(genres.isEmpty());
        assertEquals(6, genres.size());
    }

    @Test
    @DisplayName("Test findGenreById - Cas droit")
    void testFindGenreById() {
        // Arrange
        long id = 1;
        // Act
        Genre genre = genreRepository.findGenreById(id);
        // Assert
        assertNotNull(genre);
        assertEquals(id, genre.getId());
    }

    @Test
    @DisplayName("Test findGenreById - Cas id n'existe pas")
    void testFindGenreByIdCaseIdDoesntExist() {
        // Arrange
        long id = 99;
        // Act and assert
        assertThrows(GenreNotFoundException.class, () -> genreRepository.findGenreById(id));
    }
}
