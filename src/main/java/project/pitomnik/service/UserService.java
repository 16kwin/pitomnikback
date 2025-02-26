package project.pitomnik.service;

import project.pitomnik.model.Role;
import project.pitomnik.model.User;
import project.pitomnik.repository.RoleRepository;
import project.pitomnik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String password, String firstname, String lastname, String roleName) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists"); 
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); 
        user.setFirstname(firstname);
        user.setLastname(lastname);

        Role role = roleRepository.findByName(roleName);
        if(role == null){
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
        user.setRole(role);

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
