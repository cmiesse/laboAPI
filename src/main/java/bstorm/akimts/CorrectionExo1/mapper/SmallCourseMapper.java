package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.SmallCourseDTO;
import bstorm.akimts.CorrectionExo1.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class SmallCourseMapper {

    public SmallCourseDTO toDTO(Course entity){
        if( entity == null )
            return null;

        return SmallCourseDTO.builder()
                .id(entity.getId())
                .ects( entity.getEcts())
                .name(entity.getName())
                .build();
    }
}
