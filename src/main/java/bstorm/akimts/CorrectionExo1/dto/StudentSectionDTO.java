package bstorm.akimts.CorrectionExo1.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class StudentSectionDTO {

    private Integer id;
    private String firstname;
    private String lastname;

}
