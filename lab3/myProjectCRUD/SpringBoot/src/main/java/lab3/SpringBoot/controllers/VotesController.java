package lab3.SpringBoot.controllers;

import lab3.SpringBoot.DTO.VotesDTO;
import lab3.SpringBoot.Entity.Movies;
import lab3.SpringBoot.Entity.Votes;
import lab3.SpringBoot.Services.MoviesService;
import lab3.SpringBoot.Services.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/votos")
public class VotesController {

    @Autowired
    private VotesService votesService;

    @Autowired
    private MoviesService moviesService;


    @PostMapping
    public Votes saveVote(@RequestBody VotesDTO votesDTO) {
        System.out.println("Nome do filme recebido (VotesController): \"" + votesDTO.getName_mov() + "\"");

        Optional<Movies> moviesOptional = moviesService.findByName_mov(votesDTO.getName_mov());

        if (moviesOptional.isEmpty()) {
            throw new RuntimeException("Filme não encontrado");
        }

        Movies movie = moviesOptional.get();

        Votes vote = new Votes();
        vote.setEmail_user(votesDTO.getEmail_user());
        vote.setCritic_voto(votesDTO.getCritic_voto());
        vote.setMovie(movie);
        return votesService.save(vote);
    }
}
