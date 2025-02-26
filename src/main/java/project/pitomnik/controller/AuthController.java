package project.pitomnik.controller;

import project.pitomnik.model.User;
import project.pitomnik.service.UserService;
import project.pitomnik.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            User registeredUser = userService.registerNewUser(
                    registrationRequest.getUsername(),
                    registrationRequest.getPassword(),
                    registrationRequest.getFirstname(),
                    registrationRequest.getLastname(),
                    registrationRequest.getRole() 
            );
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername()); // Ищем пользователя в базе данных
        final String jwtToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build());
    }

    static class RegistrationRequest { 
        private String username;
        private String password;
        private String firstname;
        private String lastname;
        private String role;  


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}