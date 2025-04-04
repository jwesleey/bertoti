package lab3.SpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class voteDTO {
    private Integer idMov;      // ID do filme
    private String emailUser;   // Email do usuário
    private String criticVoto;
}
