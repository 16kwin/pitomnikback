package project.pitomnik.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "animalbreed")
public class AnimalBreed {
    @Id
    @Column(name = "breed")
    private String breed;

    @ManyToOne
    @JoinColumn(name = "type")
    private AnimalType type;

    @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Main> animals;


    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public List<Main> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Main> animals) {
        this.animals = animals;
    }
}