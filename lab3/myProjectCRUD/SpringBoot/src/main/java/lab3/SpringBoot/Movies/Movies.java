package lab3.SpringBoot.Movies;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov", nullable = false)
    private Integer id;

    @Column(name = "name_mov", length = 50)
    private String nameMov;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMov() {
        return nameMov;
    }

    public void setNameMov(String nameMov) {
        this.nameMov = nameMov;
    }

}