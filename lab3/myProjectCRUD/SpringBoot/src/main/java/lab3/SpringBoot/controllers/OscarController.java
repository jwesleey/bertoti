package lab3.SpringBoot.controllers;

import lab3.SpringBoot.Movies.Movies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class OscarController {

    @GetMapping
    public ResponseEntity<String> getResponse() {
        return ResponseEntity.ok("OK");
    }
}
