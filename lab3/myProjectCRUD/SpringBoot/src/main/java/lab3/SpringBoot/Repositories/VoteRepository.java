package lab3.SpringBoot.Repositories;

import lab3.SpringBoot.Movies.Vote;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
