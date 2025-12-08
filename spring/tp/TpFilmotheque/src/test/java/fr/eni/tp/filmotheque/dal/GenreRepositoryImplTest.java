package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exception.GenreNotFoundException;
import fr.eni.tp.filmotheque.exception.GenreTitreAlreadyExistsException;
import org.junit.jupiter.api.AfterAll;
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
        int id = 1;
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
        int id = 99;
        // Act and assert
        assertThrows(GenreNotFoundException.class, () -> genreRepository.findGenreById(id));
    }

    @Test
    @DisplayName("Test createGenre - Cas droit")
    void testCreateGenre() {
        // Arrange
        Genre genre = new Genre(98, "Fantastique");

        // Act
        genreRepository.createGenre(genre);
        Genre newGenre = genreRepository.findGenreById(genre.getId());

        // Assert
        assertNotNull(newGenre);
        assertEquals(newGenre, genre);
    }

    @Test
    @DisplayName("Test createGenre - Cas titre existe déjà")
    void testCreateGenreCaseTitreExist() {
        // Arrange + act
        Genre genre = new Genre(98, "Genre test");
        genreRepository.createGenre(genre);

        Genre genre2 = new Genre(99, "Genre test");

        // Assert
        assertThrows(GenreTitreAlreadyExistsException.class,
                () -> genreRepository.createGenre(genre2)
        );

        genreRepository.deleteGenreById(genre2.getId());
    }

    @Test
    @DisplayName("Test updateGenre - Cas droit")
    void testUpdateGenre() {
        // Arrange
        String titre = "Fantastique";
        Genre newGenre = new Genre(98, titre);
        genreRepository.createGenre(newGenre);

        // Act
        newGenre.setTitre("Updated Titre test");
        genreRepository.updateGenre(newGenre);

        // Assert
        assertNotEquals(titre, genreRepository.findGenreById(newGenre.getId()).getTitre());
        genreRepository.deleteGenreById(newGenre.getId());
    }

    @Test
    @DisplayName("Test updateGenre - Cas id inexistant")
    void testUpdateGenreCaseIdDoesntExist() {
        // Arrange
        int id = 97;
        Genre genre = new Genre(id, "Update genre test");

        // Act
        genreRepository.updateGenre(genre);

        // Assert
        assertThrows(GenreNotFoundException.class, () -> genreRepository.findGenreById(id));
    }
}
