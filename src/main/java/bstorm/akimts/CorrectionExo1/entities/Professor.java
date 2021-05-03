package bstorm.akimts.CorrectionExo1.entities;

import bstorm.akimts.CorrectionExo1.constraints.Waged;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Professor {

    @Id
    @Column(name = "professor_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    int id;
    @Column(name = "professor_name")
    String name;
    @Column(name = "professor_surname")
    String surname;
    @Column(name = "professor_office")
    int office;
    @Column(name = "professor_email")
    String email;
    @Column(name = "professor_hire_date")
    LocalDateTime hireDate;
    @Column(name = "professor_wage")
    int wage;

    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;

    @OneToMany(mappedBy = "professor")
    List<Course> coursList;
}
