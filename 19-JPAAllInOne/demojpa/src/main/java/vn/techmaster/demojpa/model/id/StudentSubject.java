package vn.techmaster.demojpa.model.id;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@IdClass(StudentSubjectId.class)
public class StudentSubject {
    @Id
    private String studentId;

    @Id
    private String subjectId;

    private int score;
}

