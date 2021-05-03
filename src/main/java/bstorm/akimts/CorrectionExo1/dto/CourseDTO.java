package bstorm.akimts.CorrectionExo1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {

    private String id;
    private String name;
    private BigDecimal ects;
    private SmallProfessorDTO prof;

}
