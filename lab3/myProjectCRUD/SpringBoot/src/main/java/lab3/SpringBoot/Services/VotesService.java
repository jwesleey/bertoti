package lab3.SpringBoot.Services;

import lab3.SpringBoot.Entity.Votes;
import lab3.SpringBoot.Repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VotesService {

    @Autowired
    private VotesRepository votesRepository;

    public Votes save(Votes vote) {
        return votesRepository.save(vote);
    }
}
