package project.pitomnik.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "dimension")
public class Dimension {
    @Id
    @Column(name = "dimension")
    private String dimension;

    @OneToMany(mappedBy = "dimension", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Main> animals;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public List<Main> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Main> animals) {
        this.animals = animals;
    }
}