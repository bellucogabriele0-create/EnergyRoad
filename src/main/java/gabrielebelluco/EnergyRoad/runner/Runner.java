package gabrielebelluco.EnergyRoad.runner;


import gabrielebelluco.EnergyRoad.entities.Role;
import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.payloads.UserCreateDTO;
import gabrielebelluco.EnergyRoad.repositories.RoleRepository;
import gabrielebelluco.EnergyRoad.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserService userService;
    @Value("${founder.email}")
    private String founderEmail;
    @Value("${founder.pwd}")
    private String founderPwd;
    @Value("${founder.name}")
    private String founderName;
    @Value("${founder.lastname}")
    private String founderLastName;

    public Runner(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (RoleType roleType : RoleType.values()) {
            if (!roleRepository.existsByRoleType(roleType)) {
                Role role = new Role();
                role.setRoleType(roleType);
                roleRepository.save(role);
            }
        }
        System.out.println("Ruoli caricati nel database");
        if (!userService.existsByEmail(founderEmail)) {
            UserCreateDTO founderDTO = new UserCreateDTO(
                    founderName,
                    founderLastName,
                    founderPwd,
                    founderEmail
            );
            User founder = userService.createUser(founderDTO, RoleType.FOUNDER);
            System.out.println("FOUNDER creato: " + founder.getEmail());
        } else {
            System.out.println("FOUNDER già esistente nel sistema");
        }
    }
}