package bstorm.akimts.CorrectionExo1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmallProfessorDTO {

    private Integer id;
    private String name;
    private String surname;
    private Integer office;
    private String email;

}
