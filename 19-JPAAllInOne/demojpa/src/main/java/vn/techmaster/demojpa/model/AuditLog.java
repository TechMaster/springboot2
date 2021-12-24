package vn.techmaster.demojpa.model;

import java.time.LocalDateTime;

import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "auditlog")
@Table(name = "auditlog")
@Data
public class AuditLog {
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String message;
  private LocalDateTime createdAt;
  private LocalDateTime lastUpdate;

  @PrePersist // Trước khi lưu khi khởi tạo record
  public void prePersist() {
    System.out.println("Pre persist");
    createdAt = LocalDateTime.now();
  }

  @PreUpdate // Khi cập nhật record
  public void preUpdate() {
    System.out.println("Pre update");
    lastUpdate = LocalDateTime.now();
  }

  @PreRemove
  public void preRemove() {
    System.out.println("Do something before record is being deleted");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("Do something before record is being destroyed");
  }
}
