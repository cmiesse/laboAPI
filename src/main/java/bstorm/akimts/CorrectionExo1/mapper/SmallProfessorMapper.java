package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.SmallProfessorDTO;
import bstorm.akimts.CorrectionExo1.entities.Professor;
import org.springframework.stereotype.Component;

@Component
public class SmallProfessorMapper {

    public SmallProfessorDTO toDTO(Professor entity){
        if( entity == null )
            return null;

        return SmallProfessorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .office(entity.getOffice())
                .build();
    }

}
