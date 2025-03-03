package project.pitomnik.repository;

import project.pitomnik.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, String> {
}