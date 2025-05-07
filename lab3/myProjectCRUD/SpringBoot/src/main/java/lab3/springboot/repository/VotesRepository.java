package lab3.springboot.repository;


import lab3.springboot.entity.Movies;
import lab3.springboot.entity.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {
    List<Votes> findByMovie(Movies movie);

    @Query("SELECT COUNT(v) FROM Votes v WHERE v.movie.id_mov = :id_mov")
    int countByMovie(@Param("id_mov") int idMov);

    @Modifying
    @Query("DELETE FROM Votes v WHERE v.emailUser = :email_user")
    void deleteByEmailUser(@Param("email_user") String emailUser);

    List<Votes> getAllByEmailUser(@Param("email_user") String emailUser);

    @Modifying
    @Query("UPDATE Votes v SET  v.criticVote = :critic_voto WHERE v.emailUser = :email_user")
    void updateVote(@Param("email_user") String emailUser, @Param("critic_voto") String criticVote);

    @Query("SELECT  v.movie.name_mov AS name_mov, COUNT(v) AS vote_count " +
            " FROM Votes v " +
            "GROUP BY v.movie.id_mov, v.movie.name_mov " +
            "ORDER BY vote_count DESC")
    List<Object[]> getVotesRanking();
}
