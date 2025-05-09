package lab3.springboot.controllers;

import lab3.springboot.dto.VotesDTO;
import lab3.springboot.entity.Movies;
import lab3.springboot.entity.Votes;
import lab3.springboot.services.MoviesService;
import lab3.springboot.services.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("api")
public class VotesController {

    @Autowired
    private VotesService votesService;

    @Autowired
    private MoviesService moviesService;

    @PostMapping("/votar")
    public Votes saveVote(@RequestBody VotesDTO votesDTO) {

        Optional<Movies> moviesOptional = moviesService.findByName_mov(votesDTO.getName_mov());

        if (moviesOptional.isEmpty()) {
            throw new RuntimeException("Filme não encontrado");
        }

        if (votesService.isVoted(votesDTO.getEmail_user())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já utilizado para votação");
        } else {
            Movies movie = moviesOptional.get();
            Votes vote = new Votes();
            vote.setEmailUser(votesDTO.getEmail_user());
            vote.setCriticVote(votesDTO.getCritic_voto());
            vote.setMovie(movie);
            return votesService.save(vote);
        }
    }

    @GetMapping("/ranking")
    public List<Map<String, Object>> getRanking() {
        List<Object[]> rankingData = votesService.getVotesRanking();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] data : rankingData) {
            Map<String, Object> movieData = new HashMap<>();
            movieData.put("name_mov", data[0]);
            movieData.put("vote_count", ((Number) data[1]).intValue());
            result.add(movieData);
        }
        return result;
    }

    @GetMapping("/meuvoto")
    public List<Votes> getVotesByEmail(@RequestParam("email_user") String emailUser) {
        return votesService.findByEmailUser(emailUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteVote(@RequestBody String emailUser) {
        votesService.deleteVote(emailUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Voto deletado com sucesso" + emailUser);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar")
    public ResponseEntity<Map<String, String>> updateVote(@RequestBody VotesDTO votesDTO) {
        if (votesDTO.getCritic_voto() == null || votesDTO.getCritic_voto().trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "A crítica está vazia");
            return ResponseEntity.badRequest().body(error);
        }
        votesService.updateVote(votesDTO.getEmail_user(), votesDTO.getCritic_voto());
        Map<String, String> success = new HashMap<>();
        success.put("message", "Voto atualizado com sucesso");
        return ResponseEntity.ok(success);
    }
}