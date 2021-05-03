package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.dto.SectionDTO;
import bstorm.akimts.CorrectionExo1.dto.StudentDTO;
import bstorm.akimts.CorrectionExo1.entities.Section;
import bstorm.akimts.CorrectionExo1.entities.Student;
import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.mapper.Mapper;
import bstorm.akimts.CorrectionExo1.repository.SectionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService implements CrudService<SectionDTO, Integer> {

    private final SectionRepository repo;
    private final Mapper<SectionDTO, Section> mapper;
    private final Mapper<StudentDTO, Student> sMapper;

    public SectionService(SectionRepository repo, Mapper<SectionDTO, Section> mapper, Mapper<StudentDTO, Student> sMapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.sMapper = sMapper;
    }

    @Override
    public void insert(SectionDTO toInsert) throws ElementAlreadyExistsException {
        if( toInsert == null )
            throw new IllegalArgumentException();

        if( repo.existsById(toInsert.getId()) )
            throw new ElementAlreadyExistsException();

        repo.save( mapper.toEntity(toInsert) );
    }

    @Override
    @Transactional
    public SectionDTO getOne(Integer integer) throws ElementNotFoundException {
        if( integer == null )
            throw new IllegalArgumentException();

        if( !repo.existsById(integer) )
            throw new ElementNotFoundException(integer);

        return mapper.toDTO( repo.getOne(integer) );
    }

    @Override
    public List<SectionDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(SectionDTO toUpdate, Integer integer) throws ElementNotFoundException {
        if( integer == null  || toUpdate == null )
            throw new IllegalArgumentException();

        if( !repo.existsById(integer) )
            throw new ElementNotFoundException(integer);

        Section s = repo.getOne(integer);
        s.setName(toUpdate.getName());
        s.setDelegateId(toUpdate.getDelegateId());

        repo.save(s);
    }

    @Override
    public void delete(Integer integer) throws ElementNotFoundException {
        if( integer == null )
            throw new IllegalArgumentException();

        if( !repo.existsById(integer) )
            throw new ElementNotFoundException(integer);

        repo.deleteById(integer);
    }

    public List<StudentDTO> getStudentsBySectionId(Integer sectionId) throws ElementNotFoundException {
        if( sectionId == null )
            throw new IllegalArgumentException();
        if( !repo.existsById(sectionId) )
            throw new ElementNotFoundException(sectionId);

        Section section = repo.getOne(sectionId);
        return section.getStudents().stream()
                .map(sMapper::toDTO)
                .collect(Collectors.toList());
    }
}
