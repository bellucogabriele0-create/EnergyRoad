package gabrielebelluco.EnergyRoad.controllers;


import gabrielebelluco.EnergyRoad.entities.EnergySite;
import gabrielebelluco.EnergyRoad.payloads.EnergySiteCreateDTO;
import gabrielebelluco.EnergyRoad.services.EnergySiteService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site")
public class EnergySiteControler {
    private final EnergySiteService energySiteService;

    public EnergySiteControler(EnergySiteService energySiteService) {
        this.energySiteService = energySiteService;
    }

    @PostMapping
    //@PreAuthorize("hasAnyAuthority('ADMIN','FOUNDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public EnergySite create(@RequestBody @Validated EnergySiteCreateDTO dto) throws BadRequestException {
        return energySiteService.create(dto);
    }
}
