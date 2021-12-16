package vn.techmaster.demojpa.model.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Data
@Entity(name="demotableid")
@Table(name="demotableid")
public class TableID {
  @TableGenerator(name = "table_id_generator", 
    table = "table_id",
    pkColumnName = "id", 
    valueColumnName = "value",
    allocationSize = 10)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_id_generator")
  private Long id;

  private String name;
}
