package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ParticipantsRepositoryImpl implements ParticipantRepository{
    private JdbcTemplate jdbcTemplate;

    public ParticipantsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Participant> findAllParticipants() {
        String sql = "select id, prenom, nom from participants";
        return jdbcTemplate.query(sql, new ParticipantRowMapper());
    }

    @Override
    public Participant findParticipantById(Integer id) {
        String sql = "select id, prenom, nom from participants where id = ?";
        return jdbcTemplate.queryForObject(sql, new ParticipantRowMapper(), id);
    }


    class ParticipantRowMapper implements RowMapper<Participant> {
        public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Participant participant = new Participant();
            participant.setId(rs.getInt("id"));
            participant.setPrenom(rs.getString("prenom"));
            participant.setNom(rs.getString("nom"));
            return participant;
        }
    }
}
