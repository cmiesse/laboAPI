package bstorm.akimts.CorrectionExo1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @Column(name = "course_id")
    private String id;
    @Column(name = "course_name")
    private String name;
    @Column(name = "course_ects")
    private BigDecimal ects;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

}
