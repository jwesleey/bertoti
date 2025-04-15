package lab3.springboot.services;

import lab3.springboot.entity.Votes;
import lab3.springboot.repository.VotesRepository;
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
