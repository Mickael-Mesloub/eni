package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exception.GenreNotFoundException;
import fr.eni.tp.filmotheque.exception.GenreTitreAlreadyExistsException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    private JdbcTemplate jdbcTemplate;

    public GenreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAllGenres() {
        String sql = "select id, titre from genres";
        return jdbcTemplate.query(sql, new GenreRowMapper());
    }

    @Override
    public Genre findGenreById(int id) {
        String sql = "select id, titre from genres where id = ?";
        Genre genre = null;

        try {
            genre = jdbcTemplate.queryForObject(sql, new GenreRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new GenreNotFoundException(id);
        }

        return genre;
    }

    @Override
    public void createGenre(Genre genre) {
        try {
            String sql = "insert into genres (id, titre) values (?, ?)";
            jdbcTemplate.update(sql, genre.getId(), genre.getTitre());
        } catch (DataAccessException ex) {
            // Si le titre existe déjà, on renvoie une exception
            if (ex instanceof DuplicateKeyException) {
                throw new GenreTitreAlreadyExistsException();
            }
            throw ex; // autre erreur SQL
        }
    }

    @Override
    public void updateGenre(Genre genre) {
        String sql = "update genres set titre = ? where id = ?";
        try {
            jdbcTemplate.update(sql, genre.getTitre(), genre.getId());
        }  catch (DataAccessException ex) {
            if (ex instanceof DuplicateKeyException) {
                throw new GenreTitreAlreadyExistsException();
            }
            throw ex;
        }
    }

    @Override
    public void deleteGenreById(int id) {
        String sql = "delete from genres where id = ?";
        jdbcTemplate.update(sql, id);
    }

    class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setId(rs.getInt("id"));
            genre.setTitre(rs.getString("titre"));

            return genre;
        }
    }
}
