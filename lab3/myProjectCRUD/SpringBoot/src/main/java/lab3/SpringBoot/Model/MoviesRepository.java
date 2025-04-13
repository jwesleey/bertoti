package lab3.SpringBoot.Model;

import lab3.SpringBoot.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    @Query("SELECT m FROM Movies m WHERE m.name_mov = :nameMov")
    Optional<Movies> findByName_mov(@Param("nameMov") String nameMov);
}
