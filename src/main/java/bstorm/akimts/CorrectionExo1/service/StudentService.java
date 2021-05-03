package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.dto.StudentDTO;
import bstorm.akimts.CorrectionExo1.entities.Student;
import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.mapper.Mapper;
import bstorm.akimts.CorrectionExo1.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements CrudService<StudentDTO, Integer> {

    private final StudentRepository repo;
    private final Mapper<StudentDTO, Student> mapper;

    public StudentService(StudentRepository repo, Mapper<StudentDTO, Student> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public void insert(StudentDTO toInsert) throws ElementAlreadyExistsException {
        if( toInsert == null )
            throw new IllegalArgumentException();

        if( repo.existsById(toInsert.getId()) )
            throw new ElementAlreadyExistsException();

        repo.save( mapper.toEntity(toInsert) );
    }

    @Override
    public StudentDTO getOne(Integer id) throws ElementNotFoundException {
        if( id == null )
            throw new IllegalArgumentException();

        Student entity = repo.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id));

        return mapper.toDTO( entity );
    }

    @Override
    public List<StudentDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(StudentDTO toUpdate, Integer id) throws ElementNotFoundException {
        if ( toUpdate == null || id == null )
            throw new IllegalArgumentException();

        Student s = mapper.toEntity(toUpdate);
        Student entity = repo.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id));

        entity.setFirstname(s.getFirstname());
        entity.setLastname(s.getLastname());
        entity.setBirthdate(s.getBirthdate());
        entity.setLogin(s.getLogin());
        entity.setResult(s.getResult());
        entity.setCourseId(s.getCourseId());
        entity.setSection(s.getSection());

        repo.save(entity);
    }

    @Override
    public void delete(Integer id) throws ElementNotFoundException {
        if ( id == null )
            throw new IllegalArgumentException();
        if ( !repo.existsById(id) )
            throw new ElementNotFoundException(id);

        repo.deleteById(id);
    }
}
