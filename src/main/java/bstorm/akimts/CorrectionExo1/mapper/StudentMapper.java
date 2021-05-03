package bstorm.akimts.CorrectionExo1.mapper;

import bstorm.akimts.CorrectionExo1.dto.StudentDTO;
import bstorm.akimts.CorrectionExo1.entities.Student;
import bstorm.akimts.CorrectionExo1.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<StudentDTO, Student> {

    @Autowired
    private SmallSectionMapper sMapper;
    @Autowired
    private SectionRepository repo;

    @Override
    public StudentDTO toDTO(Student s) {
        if( s == null )
            return null;

        return StudentDTO.builder()
                .id(s.getId())
                .firstname(s.getFirstname())
                .lastname(s.getLastname())
                .birthdate(s.getBirthdate())
                .login(s.getLogin())
                .courseId(s.getCourseId())
                .result(s.getResult())
                .section( sMapper.toDTO(s.getSection()) )
                .build();
    }

    @Override
    public Student toEntity(StudentDTO dto) {
        if( dto == null )
            return null;

        return Student.builder()
                .id(dto.getId())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .birthdate(dto.getBirthdate())
                .login(dto.getLogin())
                .courseId(dto.getCourseId())
                .result(dto.getResult())
                .section(dto.getSection() == null ? null : repo.getOne(dto.getSection().getId()))
                .build();
    }
}
