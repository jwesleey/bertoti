package lab3.SpringBoot.Services;

import lab3.SpringBoot.Entity.Movies;
import lab3.SpringBoot.Model.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public Optional<Movies> findByName_mov(String name_mov) {
       return moviesRepository.findByName_mov(name_mov);
    }
}

