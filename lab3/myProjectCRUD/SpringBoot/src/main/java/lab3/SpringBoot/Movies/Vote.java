package lab3.SpringBoot.Movies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto", nullable = false)
    private Integer id;

    @Column(name = "email_user", length = 100)
    private String emailUser;

    @Column(name = "critic_voto", length = 1000)
    private String criticVoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mov")
    private Movies idMov;


}