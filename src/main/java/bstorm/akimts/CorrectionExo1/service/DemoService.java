package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.dto.StudentDTO;
import bstorm.akimts.CorrectionExo1.dto.container.PagedContainer;
import bstorm.akimts.CorrectionExo1.entities.Student;
import bstorm.akimts.CorrectionExo1.mapper.StudentMapper;
import bstorm.akimts.CorrectionExo1.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DemoService {

    public final int ELEMENTS_BY_PAGE = 5;

    private final StudentRepository repo;
    private final StudentMapper mapper;

    public DemoService(StudentRepository repo, StudentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<StudentDTO> getAllStudentsPaged( int page ){

        return repo.findAll( PageRequest.of(page, ELEMENTS_BY_PAGE) )
                .map(mapper::toDTO);

    }
}
