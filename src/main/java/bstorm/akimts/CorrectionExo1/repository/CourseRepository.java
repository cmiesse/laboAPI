package bstorm.akimts.CorrectionExo1.repository;

import bstorm.akimts.CorrectionExo1.entities.Course;
import bstorm.akimts.CorrectionExo1.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    // SELECT c FROM Course c WHERE c.name = ?1
    List<Course> findByName(String name);
    // SELECT c FROM Course c WHERE c.ects >= ?1
    List<Course> findByEctsGreaterThanEqual(BigDecimal min);
    // SELECT c FROM Course c WHERE c.professor.id = ?1
    List<Course> findByProfessorId(int id);

    @Query("SELECT c FROM Course c WHERE LENGTH(c.name) = ?1")
    List<Course> findByMinNameLength(int length);

    @Transactional
    @Modifying
    @Query("DELETE FROM Course c WHERE SUBSTRING(c.name, 1, 1) = ?1")
    void deleteByNameFirstLetter(String letter);

}
