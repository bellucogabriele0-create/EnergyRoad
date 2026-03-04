package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.Role;
import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.UserCreateDTO;
import gabrielebelluco.EnergyRoad.repositories.RoleRepository;
import gabrielebelluco.EnergyRoad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con id: " + id));
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("id non trovato: " + userId));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con email: " + email));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    public User createUser(UserCreateDTO payload) {
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new IllegalArgumentException("L'email è già in uso");
        }
        User u = new User();
        u.setFirstname(payload.getFirstname());
        u.setLastname(payload.getLastname());
        u.setEmail(payload.getEmail());
        u.setPassword(passwordEncoder.encode(payload.getPassword()));
        Role ruoloUser = roleRepository.findByRoleType(RoleType.USER)
                .orElseThrow(() -> new NotFoundException("Ruolo USER non trovato nel db"));
        u.getRoles().add(ruoloUser);
        User savedUser = userRepository.save(u);
        System.out.println("nuovo USER registrato: " + payload.getFirstname());
        return savedUser;
    }

    public User createInvestor(UserCreateDTO payload) {
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new IllegalArgumentException("Email già utilizzata");
        }
        User u = new User();
        u.setFirstname(payload.getFirstname());
        u.setLastname(payload.getLastname());
        u.setEmail(payload.getEmail());
        u.setPassword(passwordEncoder.encode(payload.getPassword()));
        Role roleInvestor = roleRepository.findByRoleType(RoleType.INVESTOR)
                .orElseThrow(() -> new NotFoundException("ruolo INVESTOR non trovato"));
        u.getRoles().add(roleInvestor);
        System.out.println("nuovo INVESTOR registrato: " + payload.getFirstname());
        return userRepository.save(u);
    }


    public User createFounder(UserCreateDTO payload) {
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new IllegalArgumentException("email già utilizzata");
        }
        User u = new User();
        u.setFirstname(payload.getFirstname());
        u.setLastname(payload.getLastname());
        u.setEmail(payload.getEmail());
        u.setPassword(passwordEncoder.encode(payload.getPassword()));
        Role founderRole = roleRepository.findByRoleType(RoleType.FOUNDER)
                .orElseThrow(() -> new NotFoundException("ruolo FOUNDER non trovato"));
        u.getRoles().add(founderRole);
        return userRepository.save(u);
    }

    public User assignRoleToUser(String targetEmail, RoleType roleType, User actionUser) {
//        boolean isFounder = actionUser.getRoles().stream()
//                .anyMatch(r -> r.getRoleType() == RoleType.FOUNDER);
//        if (!isFounder) {
//            throw new UnauthorizedException("solo un FOUNDER può assegnare ruoli");
//        }
        User targetUser = userRepository.findByEmail(targetEmail)
                .orElseThrow(() -> new NotFoundException("user non trovato: " + targetEmail));
        Role role = roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new NotFoundException("role non trovato: " + roleType));
        boolean alreadyHasRole = targetUser.getRoles().stream()
                .anyMatch(r -> r.getRoleType() == roleType);
        if (alreadyHasRole) {
            throw new IllegalArgumentException("questo utente " + targetEmail + " ha già questo ruolo " + roleType);
        }
        targetUser.getRoles().clear();
        targetUser.getRoles().add(role);
        return userRepository.save(targetUser);
    }
}
