package project.pitomnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.pitomnik.model.User; // Assuming you have a User model
import project.pitomnik.service.UserService; // Assuming you have a UserService

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()") // Ensure user is authenticated
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Unauthorized"); // Or throw an exception
        }

        String username = authentication.getName(); // Get username from authentication

        User user = userService.findByUsername(username); // Assuming you have a method to find user by username

        if (user == null) {
            return ResponseEntity.notFound().build(); // Or throw an exception
        }

        return ResponseEntity.ok(user); // Return user data
    }
}