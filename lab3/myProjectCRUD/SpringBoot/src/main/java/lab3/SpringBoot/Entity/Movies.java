package lab3.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mov;

    private String name_mov;
}
