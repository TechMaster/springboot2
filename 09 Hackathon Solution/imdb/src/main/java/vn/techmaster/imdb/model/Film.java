package vn.techmaster.imdb.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    private int id;
    private String title;
    private int year;
    private String country;
    private double rating;
    private List<String> generes;
    private int cost;
    private int revenue;
    
}
