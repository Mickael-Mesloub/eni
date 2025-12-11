package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmRepositoryImplTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ParticipantRepository participantRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each test");
        jdbcTemplate.update("delete from films");
        jdbcTemplate.update("DBCC CHECKIDENT ('films', RESEED, 0);");
        jdbcTemplate.update(" insert into films (titre, annee, duree, synopsis, genreId, realisateurId)\n" +
                "values ('Jurassic Park',\n" +
                "        1993,\n" +
                "        128,\n" +
                "        N'Le film raconte l''histoire d''un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.',\n" +
                "        2,\n" +
                "        1\n" +
                "       )");
        jdbcTemplate.update("insert into films (titre, annee, duree, synopsis, genreId, realisateurId)\n" +
                "values ('The Fly',\n" +
                "        1986,\n" +
                "        95,\n" +
                "        N'Il s''agit de l''adaptation cinématographique de la nouvelle éponyme de l''auteur George Langelaan.',\n" +
                "        2,\n" +
                "        2\n" +
                "       )");
        jdbcTemplate.update("insert into films (titre, annee, duree, synopsis, genreId, realisateurId)\n" +
                "values ('The BFG',\n" +
                "        2016,\n" +
                "        117,\n" +
                "        N'Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.',\n" +
                "        5,\n" +
                "        1\n" +
                "       )");
        jdbcTemplate.update("insert into films (titre, annee, duree, synopsis, genreId, realisateurId)\n" +
                "values (  N'Bienvenue chez les Ch''tis',\n" +
                "           2008,\n" +
                "           106,\n" +
                "           N'Philippe Abrams est directeur de la poste de Salon-de-Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d''obtenir une mutation sur la Côte d''Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord.',\n" +
                "           5,\n" +
                "           3\n" +
                "       )");
    }
    
    @Test
    @DisplayName("Test findAllFilms - Cas droit")
    public void testFindAllFilms() {
        List<Film> films = filmRepository.findAllFilms();

        assertNotNull(films);
        assertFalse(films.isEmpty());
        assertEquals(4, films.size());
    }

    @Test
    @DisplayName("Test findFilmById - Cas droit")
    void testFindFilmById() {
        // Arrange
        int id = 1;

        // Act
        Film film = filmRepository.findFilmById(id);

        // Assert
        assertNotNull(film);
        assertEquals(id, film.getId());
    }

    @Test
    @DisplayName("Test saveFilm - Cas droit")
    void testCreateFilm() {
        // Arrange
        Film film = new Film("Zootopia 2", 2025,  108, "Animo rigolo");

        film.setGenre(genreRepository.findGenreById(1));
        film.setRealisateur(participantRepository.findParticipantById(6));

        // Act
        filmRepository.saveFilm(film);
        Film newFilm = filmRepository.findFilmById(film.getId());

        // Assert
        assertNotNull(newFilm);
        assertEquals(newFilm, film);

//        filmRepository.deleteFilmById(film.getId());
    }
    
}
