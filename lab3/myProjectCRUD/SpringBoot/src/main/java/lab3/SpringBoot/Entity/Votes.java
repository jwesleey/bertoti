package lab3.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Votes {

    @Id
    @Column(name = "id_voto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVote;

    @Column(unique = true, name = "email_user")
    private String emailUser;

    @Column(name = "critic_voto")
    private String criticVote;

    @ManyToOne
    @JoinColumn(name = "id_mov")
    private Movies movie;

}
