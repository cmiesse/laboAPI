package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.SmallSectionDTO;
import bstorm.akimts.CorrectionExo1.entities.Section;
import org.springframework.stereotype.Component;

@Component
public class SmallSectionMapper {

    SmallSectionDTO toDTO(Section entity){
        if( entity == null )
            return null;

        return SmallSectionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
