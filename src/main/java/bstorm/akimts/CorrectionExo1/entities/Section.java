package bstorm.akimts.CorrectionExo1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {

    @Id
    @Column(name = "section_id")
    private int id;

    @Column(name = "section_name")
    @Size(min = 5)
    private String name;

    @Column(name = "delegate_id")
    private int delegateId;

    @OneToMany(mappedBy = "section")
    List<Student> students;
}
