package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;

import java.util.List;

public interface CrudService<DTO, ID> {

    // Create
    void insert(DTO toInsert) throws ElementAlreadyExistsException;

    // Read
    DTO getOne(ID id) throws ElementNotFoundException;
    List<DTO> getAll();

    // Update
    void update(DTO toUpdate, ID id) throws ElementNotFoundException;

    // Delete
    void delete(ID id) throws ElementNotFoundException;

}
