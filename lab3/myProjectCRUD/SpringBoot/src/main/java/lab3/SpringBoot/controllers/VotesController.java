package lab3.SpringBoot.controllers;

import lab3.SpringBoot.Entity.Votes;
import lab3.SpringBoot.Services.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/votos")
public class VotesController {

    @Autowired
    VotesService votesService;

    @GetMapping
    public String test() {
        return "Funcionando";
    }

    @PostMapping
    public Votes saveVote(@RequestBody Votes vote) {
        Votes voteSaved = new Votes();
        voteSaved.setEmail_user(vote.getEmail_user());
        voteSaved.setCritic_voto(vote.getCritic_voto());
        return votesService.save(voteSaved);
    }
}
