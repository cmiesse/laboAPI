package bstorm.akimts.CorrectionExo1.repository;

import bstorm.akimts.CorrectionExo1.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    List<Professor> findByNameStartingWith(String start);
    Optional<Professor> findByOffice(int office);
    List<Professor> findByWageLessThanEqualAndWageGreaterThanEqual(int max, int min);

    @Query("SELECT p FROM Professor p WHERE LENGTH(p.name)+LENGTH(p.surname) = ?1")
    List<Professor> findByNamesCombinedLength(int length);

    @Query("SELECT p FROM Professor p WHERE p.name LIKE :name OR p.surname LIKE :surname")
    List<Professor> findByNames(
            @Param("name") String name,
            @Param("surname") String surname
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Professor p WHERE p.office = ?1")
    void deleteByOffice(int office);

}
