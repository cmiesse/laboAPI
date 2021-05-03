package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.dto.ProfessorDTO;
import bstorm.akimts.CorrectionExo1.dto.container.PagedContainer;
import bstorm.akimts.CorrectionExo1.dto.rapport.Rapport;
import bstorm.akimts.CorrectionExo1.exceptions.InvalidPageNbrException;
import bstorm.akimts.CorrectionExo1.exceptions.InvalidPageSizeException;
import bstorm.akimts.CorrectionExo1.service.ProfessorService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/prof")
@Profile("api")
public class ProfessorController extends AbstractCrudController<ProfessorDTO, Integer> {
    public ProfessorController(ProfessorService service) {
        super(service);
    }

    @GetMapping("/paged/{pageNbr}")
    @ResponseStatus(HttpStatus.OK)
    public PagedContainer<ProfessorDTO> getPaged(@PathVariable int pageNbr, @RequestParam(value = "size", required = false) Integer nbrElements) throws InvalidPageSizeException, InvalidPageNbrException {
        if( nbrElements == null )
            nbrElements = 2;

        if( nbrElements != 2 && nbrElements != 3 )
            throw new InvalidPageSizeException(" 2 | 3 ");

        Page<ProfessorDTO> page = ((ProfessorService)service).getPage(pageNbr, nbrElements);

        if( page.isEmpty() )
            throw new InvalidPageNbrException(pageNbr, page.getTotalPages());

        PagedContainer<ProfessorDTO> container = new PagedContainer<>(
                page.getContent(),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );

        if( page.hasNext() )
            container.setNextPage("/prof/paged/"+ page.nextPageable().getPageNumber() );

        if( page.hasPrevious() )
            container.setPreviousPage( "/prof/paged/"+ page.previousPageable().getPageNumber() );

        return container;
    }

    @GetMapping("/name_start")
    public List<ProfessorDTO> getByNameStart(@RequestParam(defaultValue = "") String start){

        return ((ProfessorService)service).getByNameStartingWith(start);

    }

    @GetMapping("/by_wage")
    public List<ProfessorDTO> getByWageBetween(
            @RequestParam(defaultValue = "0") int min,
            @RequestParam(defaultValue = Integer.MAX_VALUE+"") int max
    ){
        return ((ProfessorService)service).getByWageBetween(min,max);
    }

    @GetMapping("/names_length")
    public List<ProfessorDTO> getByNamesCombinedLength(@RequestParam int length){
        return ((ProfessorService)service).getByNamesCombinedLength(length);
    }

    @GetMapping("/by_names")
    public List<ProfessorDTO> getByNames(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String surname
    ) {
        return ((ProfessorService)service).getByNames(name,surname);
    }

    @DeleteMapping("/by_office")
    public void deleteByOffice(@RequestParam int office){
        ((ProfessorService)service).deleteByOffice(office);
    }
}
