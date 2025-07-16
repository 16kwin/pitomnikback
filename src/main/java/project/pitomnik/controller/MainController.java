package project.pitomnik.controller;

import project.pitomnik.model.Main;
import project.pitomnik.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap; // Import HashMap
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animals")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/names")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Map<String, Object>>> getAnimalNames() {
        List<Main> animals = mainService.getAllAnimals();
        List<Map<String, Object>> animalNames = animals.stream()
                .map(animal -> {
                    Map<String, Object> map = new HashMap<>(); // Use HashMap
                    map.put("id", animal.getUID());
                    map.put("name", animal.getName());
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(animalNames);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Main> getAnimalById(@PathVariable Integer id) {
        Main animal = mainService.getAnimalById(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }
}