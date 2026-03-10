package gabrielebelluco.EnergyRoad.payloads;

import jakarta.validation.constraints.NotBlank;

public record CreateProductCategoryDTO(
        @NotBlank(message = "il nome della categoria è obbligatorio ")
        String name) {
}
