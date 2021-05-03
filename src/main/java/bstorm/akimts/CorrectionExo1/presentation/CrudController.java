package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<DTO, ID> {

    // Create
    ResponseEntity<DTO> create(DTO dto) throws ElementAlreadyExistsException, ElementNotFoundException;

    // Read
    ResponseEntity<DTO> getOne(ID id) throws ElementNotFoundException;
    List<DTO> getAll();

    // Update
    ResponseEntity<DTO> update(DTO dto, ID id) throws ElementNotFoundException;

    // Delete
    ResponseEntity<DTO> delete(ID id) throws ElementNotFoundException;

}
