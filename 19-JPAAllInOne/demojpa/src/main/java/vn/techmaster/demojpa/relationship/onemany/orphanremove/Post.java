package vn.techmaster.demojpa.relationship.onemany.orphanremove;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @Table @Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  public Post(String title) {
    this.title = title;
  }

  @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
  @JoinColumn(name = "post_id")
  private List<Comment> comments = new ArrayList<>();

  public void addComment(Comment comment) {
    comment.setPost(this);
    comments.add(comment);    
  }

  public void removeComment(Comment comment) {
    comment.setPost(null);
    comments.remove(comment);   
  }
}