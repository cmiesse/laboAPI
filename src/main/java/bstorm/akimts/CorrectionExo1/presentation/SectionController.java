package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.dto.SectionDTO;
import bstorm.akimts.CorrectionExo1.dto.StudentDTO;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.service.CrudService;
import bstorm.akimts.CorrectionExo1.service.SectionService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section") // localhost:8080/section
@Profile("api")
public class SectionController extends AbstractCrudController<SectionDTO, Integer> {

    public SectionController(CrudService<SectionDTO, Integer> service) {
        super(service);
    }

//    @Override
//    @PostMapping// POST - localhost:8080/section
//    public ResponseEntity<SectionDTO> create(@RequestBody SectionDTO sectionDTO ){
//
//        try {
//            service.insert(sectionDTO);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("fromController", "SectionController");
//            headers.add("fromController", "SectionController2");
//
//            return new ResponseEntity<SectionDTO>(
//                    sectionDTO,
//                    headers,
//                    HttpStatus.CREATED
//            );
//
//        } catch (ElementAlreadyExistsException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .header("fromController", "SectionController", "SectionController2")
//                    .build();
//        }
//    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<StudentDTO>> getStudentFromSection(@PathVariable("id") Integer id) throws ElementNotFoundException {
        List<StudentDTO> studentDTOS = ((SectionService)service).getStudentsBySectionId(id);
        return ResponseEntity.ok(studentDTOS);
    }

}
