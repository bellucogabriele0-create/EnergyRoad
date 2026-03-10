package gabrielebelluco.EnergyRoad.controllers;


import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteDTO;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteResponseDTO;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteStatusDTO;
import gabrielebelluco.EnergyRoad.services.EnergySiteService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/site")
public class EnergySiteController {
    private final EnergySiteService energySiteService;

    public EnergySiteController(EnergySiteService energySiteService) {
        this.energySiteService = energySiteService;
    }

    @GetMapping
    public List<EnergySiteResponseDTO> getAll() {
        return energySiteService.getAll().stream()
                .map(EnergySiteResponseDTO::from)
                .toList();
    }

    @GetMapping("/{id}")
    public EnergySiteResponseDTO getById(@PathVariable UUID id) {
        return EnergySiteResponseDTO.from(energySiteService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public EnergySiteResponseDTO create(@RequestBody @Valid EnergySiteDTO dto) throws BadRequestException {
        return EnergySiteResponseDTO.from(energySiteService.create(dto));
    }

    @GetMapping("/status/{status}")
    public List<EnergySiteResponseDTO> getByStatus(@PathVariable EnergySiteStatus status) {
        return energySiteService.getByStatus(status).stream()
                .map(EnergySiteResponseDTO::from)
                .toList();
    }

    @GetMapping("/type/{type}")
    public List<EnergySiteResponseDTO> getByType(@PathVariable EnergySiteType type) {
        return energySiteService.getByType(type).stream()
                .map(EnergySiteResponseDTO::from)
                .toList();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public EnergySiteResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid EnergySiteDTO dto
    ) throws BadRequestException {
        return EnergySiteResponseDTO.from(energySiteService.update(id, dto));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public EnergySiteResponseDTO updateStatus(
            @PathVariable UUID id,
            @RequestBody EnergySiteStatusDTO dto
    ) {
        return EnergySiteResponseDTO.from(energySiteService.updateStatus(id, dto.status()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        energySiteService.delete(id);
    }
}
