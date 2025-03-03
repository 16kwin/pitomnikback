package project.pitomnik.repository;

import project.pitomnik.model.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteersRepository extends JpaRepository<Volunteers, Integer> {
}