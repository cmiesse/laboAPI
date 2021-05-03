package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.dto.CourseDTO;
import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO insert(@RequestBody CourseDTO cours) throws ElementAlreadyExistsException {
        return service.insert(cours);
    }

    @GetMapping
    public List<CourseDTO> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/{id}/delete")
    public CourseDTO delete(@PathVariable String id) throws ElementNotFoundException {
        CourseDTO toDelete = this.getOneById(id);
        service.delete(id);
        return toDelete;
    }

    @GetMapping("/{id}")
    public CourseDTO getOneById(@PathVariable String id) {return service.getOneById(id);}
    @GetMapping("/by_name")
    public List<CourseDTO> getByName(@RequestParam String name){
        return service.getByName(name);
    }

    @GetMapping("/by_min_ects")
    public List<CourseDTO> getByMinECTS(@RequestParam("min") float minECTS){
        return service.getByMinECTS( BigDecimal.valueOf(minECTS) );
    }

    @GetMapping("/by_prof")
    public List<CourseDTO> getByProfessor(@RequestParam("prof_id") int profId){
        return service.getByProfessor(profId);
    }

    @GetMapping("/by_name_length")
    public List<CourseDTO> getByNameLength(@RequestParam("name_length") int nameLength){
        return service.getByNameLength(nameLength);
    }

    @DeleteMapping("/by_firstLetter")
    public long getByName(@RequestParam("letter") char firstLetter){
        return service.deleteByFirstLetterInName(firstLetter);
    }
}
