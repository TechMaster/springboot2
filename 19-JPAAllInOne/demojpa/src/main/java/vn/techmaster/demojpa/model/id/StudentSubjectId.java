package vn.techmaster.demojpa.model.id;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentSubjectId implements Serializable {
  private String studentId;
  private String subjectId;
}