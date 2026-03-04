package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.repositories.EnergySiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnergySiteService {
    private final EnergySiteRepository energySiteRepository;

    public EnergySiteService(EnergySiteRepository energySiteRepository) {
        this.energySiteRepository = energySiteRepository;
    }

    public List<EnergySite> getAll() {
        return energySiteRepository.findAllByOrderByCreatedAtDesc();
    }

    public EnergySite getById(UUID id) {
        return energySiteRepository.findById(id).orElseThrow(() -> new NotFoundException("sito non trovato"));
    }
}
