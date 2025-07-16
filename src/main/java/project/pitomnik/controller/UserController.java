package project.pitomnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.pitomnik.model.User; 
import project.pitomnik.service.UserService; 

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Unauthorized"); 
        }

        String username = authentication.getName();

        User user = userService.findByUsername(username); 

        if (user == null) {
            return ResponseEntity.notFound().build(); 
        }

        return ResponseEntity.ok(user); 
    }
}