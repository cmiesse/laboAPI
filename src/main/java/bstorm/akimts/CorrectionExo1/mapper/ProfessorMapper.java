package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.ProfessorDTO;
import bstorm.akimts.CorrectionExo1.entities.Professor;
import bstorm.akimts.CorrectionExo1.repository.CourseRepository;
import bstorm.akimts.CorrectionExo1.repository.SectionRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfessorMapper implements Mapper<ProfessorDTO, Professor> {

    private final SmallSectionMapper sMapper;
    private final SmallCourseMapper courseMapper;
    private final SectionRepository sectionRepo;

    public ProfessorMapper(SmallSectionMapper mapper, SmallCourseMapper courseMapper, SectionRepository sectionRepo) {
        this.sMapper = mapper;
        this.courseMapper = courseMapper;
        this.sectionRepo = sectionRepo;

    }

    @Override
    public ProfessorDTO toDTO(Professor p) {
        if( p == null )
            return null;

        return ProfessorDTO.builder()
                .id( p.getId() )
                .name( p.getName() )
                .surname( p.getSurname() )
                .office( p.getOffice() )
                .email( p.getEmail() )
                .section( sMapper.toDTO(p.getSection()) )
                .wage( p.getWage() )
                .hireDate( p.getHireDate() )
                .cours( p.getCoursList() == null ? null : p.getCoursList().stream().map(courseMapper::toDTO).collect(Collectors.toList()) )
                .build();
    }

    @Override
    public Professor toEntity(ProfessorDTO dto) {
        if ( dto == null )
            return null;

        return Professor.builder()
                .id( dto.getId() )
                .name(dto.getName())
                .surname(dto.getSurname())
                .office(dto.getOffice())
                .email(dto.getEmail())
                .hireDate(dto.getHireDate())
                .wage(dto.getWage())
                .section( sectionRepo.getOne(dto.getSection().getId()) )
                .build();
    }
}
