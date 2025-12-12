package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.exception.FilmNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
    private String SELECT_FILM = "select f.id, f.titre, annee, duree, synopsis, realisateurId, genreId, " +
            "p.prenom as prenomReal, p.nom as nomReal, " +
            "g.titre as titreGenre from films f" + " " +
            "inner join participants p on p.id = f.realisateurId" + " " +
            "inner join genres g on g.id = f.genreId";
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(FilmRepositoryImpl.class);

    public FilmRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Film> findAllFilms() {

        return jdbcTemplate.query(SELECT_FILM, new FilmRowMapper());
    }

    @Override
    public Film findFilmById(Integer id) {
        String sql = SELECT_FILM + " where f.id = ?";

        Film film = null;

        try {
            film = jdbcTemplate.queryForObject(sql, new FilmRowMapper(), id);
            List<Participant> acteurs = findActeursByFilm(id);
            film.setActeurs(acteurs);

        } catch (EmptyResultDataAccessException exc) {
            throw new FilmNotFoundException(id);
        }

        return film;
    }

    @Override
    public void saveFilm(Film film) {
        String sql = "insert into films (titre, annee, duree, synopsis, genreId, realisateurId) "
                + " values (:titre, :annee, :duree, :synopsis, :genreId, :realisateurId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("titre", film.getTitre());
        parameters.addValue("annee", film.getAnnee());
        parameters.addValue("duree", film.getDuree());
        parameters.addValue("synopsis", film.getSynopsis());
        parameters.addValue("genreId", film.getGenre().getId());
        parameters.addValue("realisateurId", film.getRealisateur().getId());

        namedParameterJdbcTemplate.update(sql, parameters, keyHolder ,new String[] { "id"} );

        film.setId(keyHolder.getKey().intValue());

        if(film.getActeurs().size()>0) {
            sql = "insert into acteurs (filmId, participantId) values (:filmId, :participantId)";

            MapSqlParameterSource[] acteursParameters = new MapSqlParameterSource[film.getActeurs().size()];
            for(int i = 0; i < film.getActeurs().size(); i++) {
                acteursParameters[i] = new MapSqlParameterSource();
                acteursParameters[i].addValue("filmId", film.getId());
                acteursParameters[i].addValue("participantId", film.getActeurs().get(i).getId());
            }

            namedParameterJdbcTemplate.batchUpdate(sql, acteursParameters);
        }
    }

    @Override
    public void deleteFilmById(int id) {
        String sql = "delete from films where id = ?";

        Film film = null;

        try {
            film = findFilmById(id);
            jdbcTemplate.update(sql, id);

        } catch (EmptyResultDataAccessException exc) {
            logger.error("Film not found with id {}", id);
            throw new FilmNotFoundException(id);
        }
    }

    public List<Participant> findActeursByFilm(int id) {
        String sql = "select p.id as id, nom, prenom "
                + "from participants p inner join acteurs a "
                + " on p.id = a.participantid and a.filmid = ?";

        List<Participant> acteurs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Participant>(Participant.class), id);

        return acteurs;
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
