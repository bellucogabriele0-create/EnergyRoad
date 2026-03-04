package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteCreateDTO;
import gabrielebelluco.EnergyRoad.repositories.EnergySiteRepository;
import org.apache.coyote.BadRequestException;
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

    public List<EnergySite> getByStatus(EnergySiteStatus status) {
        return energySiteRepository.findByStatus(status);
    }

    public List<EnergySite> getByType(EnergySiteType type) {
        return energySiteRepository.findByType(type);
    }

    public void delete(UUID id) {
        EnergySite site = getById(id);
        energySiteRepository.delete(site);
    }

    public EnergySite create(EnergySiteCreateDTO dto) throws BadRequestException {
        if (energySiteRepository.existsByName(dto.name())) {
            throw new BadRequestException("questo sito è già esistente");
        }
        EnergySite energySite = new EnergySite(
                dto.description(),
                dto.name(),
                dto.latitude(),
                dto.longitude(),
                dto.energySiteType(),
                EnergySiteStatus.IN_SVILUPPO,
                dto.image()
        );
        return energySiteRepository.save(energySite);
    }

}
