package bstorm.akimts.CorrectionExo1.service;

import bstorm.akimts.CorrectionExo1.dto.ProfessorDTO;
import bstorm.akimts.CorrectionExo1.exceptions.InvalidPageNbrException;
import bstorm.akimts.CorrectionExo1.exceptions.InvalidPageSizeException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessorService extends CrudService<ProfessorDTO, Integer> {
    Page<ProfessorDTO> getPage(int pageNbr, int size) throws InvalidPageSizeException, InvalidPageNbrException;
    List<ProfessorDTO> getByNameStartingWith(String start);
    List<ProfessorDTO> getByWageBetween(int min, int max);
    List<ProfessorDTO> getByNamesCombinedLength(int length);
    List<ProfessorDTO> getByNames(String name, String surname);
    void deleteByOffice(int office);
}
