package lab3.SpringBoot.Movies;

import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getCriticVoto() {
        return criticVoto;
    }

    public void setCriticVoto(String criticVoto) {
        this.criticVoto = criticVoto;
    }

    public Movies getIdMov() {
        return idMov;
    }

    public void setIdMov(Movies idMov) {
        this.idMov = idMov;
    }

}