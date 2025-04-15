package lab3.springboot.repository;

import lab3.springboot.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {}
