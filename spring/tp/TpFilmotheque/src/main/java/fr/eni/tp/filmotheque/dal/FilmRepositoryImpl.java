package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
    private JdbcTemplate jdbcTemplate;

    public FilmRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> findAllFilm() {
        String sql = "select f.id, f.titre, annee, duree, synopsis, realisateurId, genreId, " +
                    "p.prenom as prenomReal, p.nom as nomReal, " +
                    "g.titre as titreGenre from films f"  + " " +
                    "inner join participants p on p.id = f.realisateurId"  + " " +
                    "inner join genres g on g.id = f.genreId";

       return jdbcTemplate.query(sql, new FilmRowMapper());
    }

    public Film findFilmById(int id) {
        String sql = "select f.id, f.titre, annee, duree, synopsis, realisateurId, genreId, " +
                "p.prenom as prenomReal, p.nom as nomReal, " +
                "g.titre as titreGenre from films f"  + " " +
                "inner join participants p on p.id = f.realisateurId"  + " " +
                "inner join genres g on g.id = f.genreId where f.id = ?";

        return jdbcTemplate.queryForObject(sql, new FilmRowMapper(), id);
    }

    @Override
    public void createFilm(Film film) {
        String sql = "insert into films (titre, annee, duree, synopsis, genreId, realisateurId)" + " " +
                "values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                film.getTitre(),
                film.getAnnee(),
                film.getDuree(),
                film.getSynopsis(),
                film.getGenre().getId(),
                film.getRealisateur().getId());

        System.out.println("Nouveau film créé : " + film.toString());
    }

    class FilmRowMapper implements RowMapper<Film> {
        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film film = new Film();
            Participant realisateur = new Participant();
            Genre genre = new Genre();

            film.setId(rs.getInt("id"));
            film.setTitre(rs.getString("titre"));
            film.setAnnee(rs.getInt("annee"));
            film.setDuree(rs.getInt("duree"));
            film.setSynopsis(rs.getString("synopsis"));

            realisateur.setId(rs.getInt("realisateurId"));
            realisateur.setNom(rs.getString("nomReal"));
            realisateur.setPrenom(rs.getString("prenomReal"));

            genre.setId(rs.getInt("genreId"));
            genre.setTitre(rs.getString("titreGenre"));

            film.setRealisateur(realisateur);
            film.setGenre(genre);

            return film;
        }
    }
}
