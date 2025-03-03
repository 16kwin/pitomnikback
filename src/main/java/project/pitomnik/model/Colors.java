package project.pitomnik.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "colors")
public class Colors {
    @Id
    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Main> animals;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Main> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Main> animals) {
        this.animals = animals;
    }
}