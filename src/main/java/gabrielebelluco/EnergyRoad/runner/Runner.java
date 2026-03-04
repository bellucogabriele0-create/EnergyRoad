package gabrielebelluco.EnergyRoad.runner;


import gabrielebelluco.EnergyRoad.entities.Role;
import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.repositories.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Runner {

    @Bean
    public ApplicationRunner loadRoles(RoleRepository roleRepository) {
        return args -> {
            for (RoleType roleType : RoleType.values()) {
                if (!roleRepository.existsByRoleType(roleType)) {
                    Role role = new Role();
                    role.setRoleType(roleType);
                    roleRepository.save(role);
                }
            }
        };
    }
}