package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteDTO;
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

    public EnergySite save(EnergySite site) {
        return energySiteRepository.save(site);
    }

    public EnergySite create(EnergySiteDTO dto) throws BadRequestException {
        if (energySiteRepository.existsByName(dto.name())) {
            throw new BadRequestException("questo sito è già esistente");
        }
        if (energySiteRepository.existsByLatitudeAndLongitude(dto.latitude(), dto.longitude())) {
            throw new BadRequestException("esiste già un sito in queste coordinate");
        }
        EnergySite energySite = new EnergySite(
                dto.name(),
                dto.description(),
                dto.latitude(),
                dto.longitude(),
                dto.targetAmount(),
                dto.energySiteType(),
                EnergySiteStatus.IN_SVILUPPO,
                dto.image()
        );
        return energySiteRepository.save(energySite);
    }

    public EnergySite update(UUID id, EnergySiteDTO dto) throws BadRequestException {
        EnergySite site = getById(id);
        site.setName(dto.name());
        site.setDescription(dto.description());
        site.setLatitude(dto.latitude());
        site.setLongitude(dto.longitude());
        site.setType(dto.energySiteType());
        site.setImage(dto.image());

        return energySiteRepository.save(site);
    }

    public EnergySite updateStatus(UUID id, EnergySiteStatus status) {
        EnergySite site = getById(id);
        site.setStatus(status);
        return energySiteRepository.save(site);
    }

}
