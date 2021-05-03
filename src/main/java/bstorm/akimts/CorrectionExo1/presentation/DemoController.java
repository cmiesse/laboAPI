package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.dto.DemoDTO;
import bstorm.akimts.CorrectionExo1.dto.StudentDTO;
import bstorm.akimts.CorrectionExo1.dto.container.PagedContainer;
import bstorm.akimts.CorrectionExo1.dto.rapport.Rapport;
import bstorm.akimts.CorrectionExo1.exceptions.DemoException;
import bstorm.akimts.CorrectionExo1.exceptions.WrongPageException;
import bstorm.akimts.CorrectionExo1.service.DemoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/demo") // http://localhost:8080/demo/truc - GET
public class DemoController {

    private final DemoService service;

    public DemoController(DemoService service) {
        this.service = service;
    }

    @GetMapping("/student/{pageNbr}")
    @ResponseStatus(HttpStatus.OK)
    public PagedContainer<StudentDTO> getPaged(@PathVariable int pageNbr) throws Exception {

        Page<StudentDTO> page = service.getAllStudentsPaged(pageNbr);

        if( pageNbr >= page.getTotalPages() )
            throw new WrongPageException(pageNbr, page.getTotalPages()-1);

        PagedContainer<StudentDTO> container = new PagedContainer<StudentDTO>(
                page.getContent(),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );

        if( page.hasNext() )
            container.setNextPage("http://localhost:8081/demo/student/" + page.nextPageable().getPageNumber());

        if( page.hasPrevious() ) {
            if( pageNbr >= page.getTotalPages() )
                container.setPreviousPage("http://localhost:8081/demo/student/" + (page.getTotalPages() - 1));
            else
                container.setPreviousPage("http://localhost:8081/demo/student/" + page.previousPageable().getPageNumber());
        }

        return container;
    }



    @PostMapping("/truc/{id}")
    public ResponseEntity<DemoDTO> truc(@RequestBody String body, @PathVariable("id") int id) {

        System.out.println(body);

        DemoDTO dto = new DemoDTO(body, 74544);
        return ResponseEntity
                .ok()
                .header("key", "value")
                .body(dto);
    }

    @GetMapping("/test2")
    public void test2(@RequestHeader("accept") String accept){
        System.out.println(accept);
    }

    @GetMapping("/params")
    public void testParams(@RequestParam Map<String, String> params){
        params.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    @GetMapping("/param")
    public void testParam(@RequestParam("id") int id ){
        System.out.println(id);
    }

    @GetMapping("/crash")
    public ResponseEntity<Integer> testCrash() throws DemoException {
        throw new DemoException();
    }

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<Rapport> demoHandler(DemoException ex, HttpServletRequest request){

        Rapport r = new Rapport(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.I_AM_A_TEAPOT.value()
        );

        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(r);
    }


}
