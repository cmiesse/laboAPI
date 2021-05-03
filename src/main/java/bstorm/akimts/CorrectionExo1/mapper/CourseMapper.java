package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.CourseDTO;
import bstorm.akimts.CorrectionExo1.entities.Course;
import bstorm.akimts.CorrectionExo1.repository.ProfessorRepository;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper implements Mapper<CourseDTO, Course> {

    private final SmallProfessorMapper professorMapper;
    private final ProfessorRepository repository;

    public CourseMapper(SmallProfessorMapper professorMapper, ProfessorRepository repository) {
        this.professorMapper = professorMapper;
        this.repository = repository;
    }

    @Override
    public CourseDTO toDTO(Course course) {
        if( course == null )
            return null;

        return CourseDTO.builder()
                .id(course.getId())
                .ects(course.getEcts())
                .name(course.getName())
                .prof( professorMapper.toDTO(course.getProfessor()) )
                .build();
    }

    @Override
    public Course toEntity(CourseDTO courseDTO) {
        if( courseDTO == null )
            return null;

        return Course.builder()
                .id(courseDTO.getId())
                .ects(courseDTO.getEcts())
                .name(courseDTO.getName())
                .professor( repository.findById( courseDTO.getProf().getId() ).orElse(null) )
                .build();
    }
}
