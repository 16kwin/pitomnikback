package project.pitomnik.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "curators")
public class Curators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "name")
    private String name;

    @Column(name = "phonenumber")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "curator", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Main> animals;

    public Integer getUID() {
        return uid;
    }

    public void setUID(Integer UID) {
        this.uid = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Main> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Main> animals) {
        this.animals = animals;
    }
}