package lab3.SpringBoot.Repository;

import lab3.SpringBoot.Entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {}
