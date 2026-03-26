package gabrielebelluco.EnergyRoad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "gabrielebelluco.EnergyRoad.repositories")
@EntityScan(basePackages = "gabrielebelluco.EnergyRoad.entities")
public class EnergyRoadApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyRoadApplication.class, args);
    }

}