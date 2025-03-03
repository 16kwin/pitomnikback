package project.pitomnik.model;

import jakarta.persistence.*;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "main")
public class Main {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @ManyToOne
    @JoinColumn(name = "typeanimal")
    @JsonManagedReference
    private AnimalType typeAnimal;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private Boolean sex;

    @ManyToOne
    @JoinColumn(name = "breed")
    @JsonManagedReference
    private AnimalBreed breed;

    @ManyToOne
    @JoinColumn(name = "curator")
    @JsonManagedReference
    private Curators curator;

    @Column(name = "chip")
    private Integer chip;

    @ManyToOne
    @JoinColumn(name = "color")
    @JsonManagedReference
    private Colors color;

    @Column(name = "charcolor")
    private String charColor;

    @ManyToOne
    @JoinColumn(name = "dimension")
    @JsonManagedReference
    private Dimension dimension;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "age")
    private Integer age;

    @Column(name = "feature")
    private String feature;

    @Column(name = "datetimein")
    private Date dateTimeIn;

    @Column(name = "featurein")
    private String featureIn;

    @ManyToOne
    @JoinColumn(name = "volunteer")
    @JsonManagedReference
    private Volunteers volunteer;

    public Integer getUID() {
        return uid;
    }

    public void setUID(Integer UID) {
        this.uid = UID;
    }

    public AnimalType getTypeAnimal() {
        return typeAnimal;
    }

    public void setTypeAnimal(AnimalType typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public AnimalBreed getBreed() {
        return breed;
    }

    public void setBreed(AnimalBreed breed) {
        this.breed = breed;
    }

    public Curators getCurator() {
        return curator;
    }

    public void setCurator(Curators curator) {
        this.curator = curator;
    }

    public Integer getChip() {
        return chip;
    }

    public void setChip(Integer chip) {
        this.chip = chip;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public String getCharColor() {
        return charColor;
    }

    public void setCharColor(String charColor) {
        this.charColor = charColor;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Date getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(Date dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public String getFeatureIn() {
        return featureIn;
    }

    public void setFeatureIn(String featureIn) {
        this.featureIn = featureIn;
    }

    public Volunteers getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteers volunteer) {
        this.volunteer = volunteer;
    }
}