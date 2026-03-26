package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.Role;
import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.request.UserCreateDTO;
import gabrielebelluco.EnergyRoad.repositories.RoleRepository;
import gabrielebelluco.EnergyRoad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato con id: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con email: " + email));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(UserCreateDTO payload, RoleType roleType) {
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new IllegalArgumentException("L'email è già in uso");
        }
        User u = new User();
        u.setFirstname(payload.getFirstname());
        u.setLastname(payload.getLastname());
        u.setEmail(payload.getEmail().trim().toLowerCase());
        u.setPassword(passwordEncoder.encode(payload.getPassword()));
        Role role = roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new NotFoundException("Ruolo " + roleType + " non trovato nel db"));
        u.getRoles().add(role);
        return userRepository.save(u);
    }

    public User assignRoleToUser(String targetEmail, RoleType roleType, User actionUser) {
        User targetUser = userRepository.findByEmail(targetEmail)
                .orElseThrow(() -> new NotFoundException("user non trovato: " + targetEmail));
        Role role = roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new NotFoundException("role non trovato: " + roleType));
        boolean alreadyHasRole = targetUser.getRoles().stream()
                .anyMatch(r -> r.getRoleType() == roleType);
        if (alreadyHasRole) {
            throw new IllegalArgumentException("questo utente ha già il ruolo " + roleType);
        }
        targetUser.getRoles().clear();
        targetUser.getRoles().add(role);
        return userRepository.save(targetUser);
    }
}
