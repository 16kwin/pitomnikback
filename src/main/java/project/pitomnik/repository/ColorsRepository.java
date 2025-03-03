package project.pitomnik.repository;

import project.pitomnik.model.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorsRepository extends JpaRepository<Colors, String> {
}