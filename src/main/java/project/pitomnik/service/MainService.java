package project.pitomnik.service;

import project.pitomnik.model.Main;
import project.pitomnik.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainRepository mainRepository;

    public List<Main> getAllAnimals() {
        return mainRepository.findAll();
    }

    public Main getAnimalById(Integer id) {
        return mainRepository.findById(id).orElse(null);
    }
}