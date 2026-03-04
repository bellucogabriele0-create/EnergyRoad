package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.repositories.EnergySiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergySiteService {
    private final EnergySiteRepository energySiteRepository;

    public EnergySiteService(EnergySiteRepository energySiteRepository) {
        this.energySiteRepository = energySiteRepository;
    }

    public List<EnergySite> getAll() {
        return energySiteRepository.findAllByOrderByCreatedAtDesc();
    }
}
