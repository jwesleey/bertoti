package lab3.SpringBoot.controllers;

import lab3.SpringBoot.Movies.Movies;
import lab3.SpringBoot.Movies.Vote;
import lab3.SpringBoot.Repositories.MoviesRepository;
import lab3.SpringBoot.Repositories.VoteRepository;
import lab3.SpringBoot.dto.voteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/votos")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @GetMapping
    public String test() {
        return "Funcionando";
    }

    @PostMapping
    public Vote saveVote(@RequestBody voteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setEmailUser(voteDTO.getEmailUser());
        vote.setCriticVoto(voteDTO.getCriticVoto());

        Movies movie = moviesRepository.findById(voteDTO.getIdMov())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        vote.setIdMov(movie);

        return voteRepository.save(vote);
    }

}
