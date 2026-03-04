package gabrielebelluco.EnergyRoad.runner;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.payloads.UserCreateDTO;
import gabrielebelluco.EnergyRoad.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FounderRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Value("${founder.email}")
    private String founderEmail;
    @Value("${founder.pwd}")
    private String founderPwd;
    @Value("${founder.name}")
    private String founderName;
    @Value("${founder.lastname}")
    private String founderLastName;

    @Override
    public void run(String... args) throws Exception {
        boolean founderExists = userService.existsByEmail(founderEmail);
        if (!founderExists) {
            UserCreateDTO founderDTO = new UserCreateDTO(
                    founderName,
                    founderLastName,
                    founderPwd,
                    founderEmail
            );
            User founder = userService.createFounder(founderDTO);
            System.out.println("FOUNDER creato: " + founder.getEmail());
        } else {
            System.out.println("FOUNDER già esistente nel sistema");
        }
    }
}