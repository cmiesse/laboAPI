package bstorm.akimts.CorrectionExo1.dto;

import bstorm.akimts.CorrectionExo1.constraints.Multiple4;
import bstorm.akimts.CorrectionExo1.constraints.Waged;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Waged
public class ProfessorDTO implements IdentifiedDTO<Integer> {

    @NotNull
    Integer id;
    @NotNull
    @Size(min = 3)
    String name;
    @NotNull
    @Size(min = 3)
    String surname;
    @NotNull
    @Min(100) @Max(999)
    @Multiple4
    Integer office;
    @NotNull
    @Email
    String email;
    @NotNull
    @PastOrPresent
    LocalDateTime hireDate;
    @NotNull
    @Min(1500)
    Integer wage;
    @NotNull
    SmallSectionDTO section;

    List<SmallCourseDTO> cours;

}
