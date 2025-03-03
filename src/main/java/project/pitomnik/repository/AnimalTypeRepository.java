package project.pitomnik.repository;

import project.pitomnik.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, String> {
}