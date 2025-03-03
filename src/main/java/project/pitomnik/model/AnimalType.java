package project.pitomnik.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "animaltype")
public class AnimalType {
    @Id
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "typeAnimal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Main> animals;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Main> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Main> animals) {
        this.animals = animals;
    }
}