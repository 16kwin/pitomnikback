package project.pitomnik.repository;

import project.pitomnik.model.AnimalBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalBreedRepository extends JpaRepository<AnimalBreed, String> {
}