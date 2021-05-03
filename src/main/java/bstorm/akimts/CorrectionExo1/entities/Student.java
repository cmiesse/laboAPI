package bstorm.akimts.CorrectionExo1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @Column(name = "student_id")
    private int id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "birth_date")
    private LocalDateTime birthdate;

    @Column
    private String login;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "year_result")
    private int result;

    @Column(name = "course_id")
    private String courseId;
}
