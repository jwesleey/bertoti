package lab3.springboot.services;

import lab3.springboot.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public Optional<lab3.springboot.entity.Movies> findByName_mov(String name_mov) {
       return moviesRepository.findByName_mov(name_mov);
    }

}

