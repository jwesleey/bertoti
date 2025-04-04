package lab3.SpringBoot.Movies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov", nullable = false)
    private Integer id;

    @Column(name = "name_mov", length = 50)
    private String nameMov;

}