package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.exception.MembreNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MembreRepositoryImpl implements MembreRepository {
    private String SELECT_MEMBER = "select id, nom, prenom, pseudo, motDePasse, admin from membres where pseudo=?";
    JdbcTemplate jdbcTemplate;

    public MembreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Membre findMembreByUsername(String pseudo) {
        Membre membre = null;
        try {
            membre = jdbcTemplate.queryForObject(SELECT_MEMBER, new MembreRowMapper(), pseudo);
        } catch (DataAccessException ex) {
            throw new MembreNotFoundException(pseudo);
        }

        return membre;
    }

    class MembreRowMapper implements RowMapper<Membre> {
        @Override
        public Membre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Membre membre = new Membre();
            membre.setId(rs.getInt("id"));
            membre.setNom(rs.getString("nom"));
            membre.setPrenom(rs.getString("prenom"));
            membre.setPseudo(rs.getString("pseudo"));
            membre.setMotDePasse(rs.getString("motDePasse"));
            membre.setAdmin(rs.getBoolean("admin"));

            return membre;
        }
    }

}
