package lab3.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_voto;

    private String email_user;
    private String critic_voto;

    @ManyToOne
    @JoinColumn(name = "id_mov")
    private Movies movie;

}
