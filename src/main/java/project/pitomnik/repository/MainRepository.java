package project.pitomnik.repository;

import project.pitomnik.model.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<Main, Integer> {
}
