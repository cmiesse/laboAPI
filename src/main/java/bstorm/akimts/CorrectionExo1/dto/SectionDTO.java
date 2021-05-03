package bstorm.akimts.CorrectionExo1.dto;

import bstorm.akimts.CorrectionExo1.constraints.Waged;
import bstorm.akimts.CorrectionExo1.dto.IdentifiedDTO;
import bstorm.akimts.CorrectionExo1.dto.StudentSectionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDTO implements IdentifiedDTO<Integer> {
    private Integer id;
    private String name;
    private int delegateId;
    private List<StudentSectionDTO> students;
}
