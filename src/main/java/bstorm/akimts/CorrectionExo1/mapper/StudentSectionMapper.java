package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.StudentSectionDTO;
import bstorm.akimts.CorrectionExo1.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentSectionMapper {

    StudentSectionDTO toDTO(Student entity){
        if( entity == null )
            return null;

        return StudentSectionDTO.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .build();
    }

}
