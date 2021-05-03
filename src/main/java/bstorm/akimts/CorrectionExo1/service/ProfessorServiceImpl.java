package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.dto.ProfessorDTO;
import bstorm.akimts.CorrectionExo1.entities.Professor;
import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.exceptions.InvalidPageNbrException;
import bstorm.akimts.CorrectionExo1.mapper.Mapper;
import bstorm.akimts.CorrectionExo1.repository.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final Mapper<ProfessorDTO, Professor> mapper;
    private final ProfessorRepository repo;

    public ProfessorServiceImpl(Mapper<ProfessorDTO, Professor> mapper, ProfessorRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public void insert(ProfessorDTO toInsert) throws ElementAlreadyExistsException {
        if( toInsert == null )
            throw new IllegalArgumentException();
        if( repo.existsById(toInsert.getId()) )
            throw new ElementAlreadyExistsException();

        repo.save(mapper.toEntity(toInsert));
    }

    @Override
    public ProfessorDTO getOne(Integer id) throws ElementNotFoundException {
        if( id == null )
            throw new IllegalArgumentException();

        return repo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ElementNotFoundException(id));
    }

    @Override
    public List<ProfessorDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProfessorDTO toUpdate, Integer id) throws ElementNotFoundException {
        if(toUpdate == null || id == null)
            throw new IllegalArgumentException();

        Professor p = mapper.toEntity(toUpdate);
        Professor prof = repo.findById(id)
                .orElseThrow(()->new ElementNotFoundException(id));

        prof.setEmail(p.getEmail());
        prof.setHireDate(p.getHireDate());
        prof.setName(p.getName());
        prof.setSection(p.getSection());
        prof.setOffice(p.getOffice());
        prof.setSurname(p.getSurname());
        prof.setWage(p.getWage());

        repo.save(prof);
    }

    @Override
    public void delete(Integer integer) throws ElementNotFoundException {
        if( integer == null )
            throw new IllegalArgumentException();
        if( !repo.existsById(integer) )
            throw new ElementNotFoundException(integer);

        repo.deleteById(integer);
    }

    @Override
    public Page<ProfessorDTO> getPage(int pageNbr, int size) throws InvalidPageNbrException {
        try{
            return repo.findAll(PageRequest.of(pageNbr, size)).map(mapper::toDTO);
        }
        catch (IllegalArgumentException e){
            throw new InvalidPageNbrException(pageNbr, (int) (repo.count() / size));
        }

    }

    @Override
    public List<ProfessorDTO> getByNameStartingWith(String start) {
        return repo.findByNameStartingWith(start).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfessorDTO> getByWageBetween(int min, int max) {
        return repo.findByWageLessThanEqualAndWageGreaterThanEqual(max, min).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfessorDTO> getByNamesCombinedLength(int length) {
        return repo.findByNamesCombinedLength(length).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfessorDTO> getByNames(String name, String surname) {
        return repo.findByNames(name, surname).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByOffice(int office) {
        repo.deleteByOffice(office);
    }
}
