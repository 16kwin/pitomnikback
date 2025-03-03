package project.pitomnik.repository;

import project.pitomnik.model.Curators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuratorsRepository extends JpaRepository<Curators, Integer> {
}