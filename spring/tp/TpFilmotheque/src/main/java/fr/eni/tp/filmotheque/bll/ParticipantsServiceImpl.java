package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParticipantsServiceImpl implements ParticipantsService {
    private ParticipantRepository participantRepository;

    public ParticipantsServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public List<Participant> consulterParticipants() {
        return participantRepository.findAllParticipants();
    }

    @Override
    public Participant consulterParticipantParId(int id) {
        return participantRepository.findParticipantById(id);
    }
}
