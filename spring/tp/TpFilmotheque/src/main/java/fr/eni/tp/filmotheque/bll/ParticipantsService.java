package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface ParticipantsService {
    List<Participant> consulterParticipants();
    Participant consulterParticipantParId(int id);
}
