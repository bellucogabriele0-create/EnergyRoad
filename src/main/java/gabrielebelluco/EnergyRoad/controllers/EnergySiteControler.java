package gabrielebelluco.EnergyRoad.controllers;


import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteDTO;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteStatusDTO;
import gabrielebelluco.EnergyRoad.services.EnergySiteService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/site")
public class EnergySiteControler {
    private final EnergySiteService energySiteService;

    public EnergySiteControler(EnergySiteService energySiteService) {
        this.energySiteService = energySiteService;
    }

    @GetMapping
    public List<EnergySite> getAll() {
        return energySiteService.getAll();
    }

    @GetMapping("/{id}")
    public EnergySite getById(@PathVariable UUID id) {
        return energySiteService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public EnergySite create(@RequestBody @Validated EnergySiteDTO dto) throws BadRequestException {
        return energySiteService.create(dto);
    }


    @GetMapping("/status/{status}")
    public List<EnergySite> getByStatus(@PathVariable EnergySiteStatus status) {
        return energySiteService.getByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<EnergySite> getByType(@PathVariable EnergySiteType type) {
        return energySiteService.getByType(type);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN','FOUNDER')")
    public EnergySite update(
            @PathVariable UUID id,
            @RequestBody EnergySiteDTO dto
    ) throws BadRequestException {
        return energySiteService.update(id, dto);
    }

    @PatchMapping("/{id}/status")
    //@PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    public EnergySite updateStatus(
            @PathVariable UUID id,
            @RequestBody EnergySiteStatusDTO dto
    ) {
        return energySiteService.updateStatus(id, dto.status());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        energySiteService.delete(id);
    }
}
