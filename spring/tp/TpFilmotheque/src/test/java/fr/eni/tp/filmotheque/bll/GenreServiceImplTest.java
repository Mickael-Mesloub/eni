package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.exception.GenreTitreAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GenreServiceImplTest {
    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Test consulterGenres - Cas droit")
    void testConsulterGenres() {
        // Arrange
        List<Genre> genres =  genreService.consulterGenres();
        int nbGenresEnBase = genreRepository.findAllGenres().size();

        // Act + Assert
        assertNotNull(genres);
        assertFalse(genres.isEmpty());
        assertEquals(nbGenresEnBase, genres.size());
    }

    @Test
    @DisplayName("Test ConsulterGenreParId - Cas droit")
    void testConsulterGenreParId() {
        // Arrange
        int id = 1;
        // Act
        Genre genre = genreService.consulterGenreParId(id);
        // Assert
        assertNotNull(genre);
        assertEquals(id, genre.getId());
    }

    @Test
    @DisplayName("Test creerGenre - Cas droit")
    void testCreerGenre() {
        // Arrange
        Genre genre = new Genre(98, "Fantastique");

        // Act
        genreService.creerGenre(genre);
        Genre newGenre = genreService.consulterGenreParId(genre.getId());

        // Assert
        assertNotNull(newGenre);
        assertEquals(newGenre, genre);
        genreRepository.deleteGenreById(genre.getId());
    }
}
