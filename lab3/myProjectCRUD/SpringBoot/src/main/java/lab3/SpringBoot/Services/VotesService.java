package lab3.springboot.services;

import lab3.springboot.entity.Votes;
import lab3.springboot.repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class VotesService {

    @Autowired
    private VotesRepository votesRepository;

    public Votes save(Votes vote) {
        return votesRepository.save(vote);
    }

    @Transactional
    public void deleteVote(String emailUser) {
        votesRepository.deleteByEmailUser(emailUser);
    }

    public List<Votes> findByEmailUser(String emailUser) {
        return votesRepository.getAllByEmailUser(emailUser);
    }

    @Transactional
    public void updateVote(String emailUser, String criticVote) {
        votesRepository.updateVote(emailUser, criticVote);
    }

    public List<Object[]> getVotesRanking(){
        return votesRepository.getVotesRanking();
    }

    public boolean isVoted(String emailUser) {
       return votesRepository.existsByEmailUser(emailUser);
    }
}