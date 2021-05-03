package bstorm.akimts.CorrectionExo1.repository;

import bstorm.akimts.CorrectionExo1.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
