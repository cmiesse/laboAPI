package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.dto.CourseDTO;
import bstorm.akimts.CorrectionExo1.entities.Course;
import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.mapper.Mapper;
import bstorm.akimts.CorrectionExo1.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final Mapper<CourseDTO, Course> mapper;

    public CourseServiceImpl(CourseRepository repo, Mapper<CourseDTO, Course> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public CourseDTO insert(CourseDTO toInsert) throws ElementAlreadyExistsException {
        if( toInsert == null )
            throw new IllegalArgumentException();
        if( repo.existsById(toInsert.getId()) )
            throw new ElementAlreadyExistsException();

        repo.save( mapper.toEntity(toInsert) );
        return mapper.toDTO( repo.getOne(toInsert.getId()) );
    }

    @Override
    public List<CourseDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getOneById(String id) {
        return repo.findById(id).map(mapper::toDTO).orElse(null);
    }

    public void delete(String index) throws ElementNotFoundException {
        if( index == null )
            throw new IllegalArgumentException();
        if( !repo.existsById(index) )
            throw new ElementNotFoundException(index);

        repo.deleteById(index);
    }


    @Override
    public List<CourseDTO> getByName(String name) {
        return repo.findByName(name).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByMinECTS(BigDecimal minECTS) {
        return repo.findByEctsGreaterThanEqual(minECTS).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByProfessor(int profId) {
        return repo.findByProfessorId(profId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByNameLength(int length) {
        return repo.findByMinNameLength(length).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long deleteByFirstLetterInName(char caractere) {
        long initialCount = repo.count();
        repo.deleteByNameFirstLetter( String.valueOf(caractere) );
        return initialCount - repo.count();
    }
}
