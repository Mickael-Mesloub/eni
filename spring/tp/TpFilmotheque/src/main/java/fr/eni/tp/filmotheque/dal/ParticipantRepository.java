package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface ParticipantRepository {
    List<Participant> findAllParticipants();
    Participant findParticipantById(Integer id);
}
