package gabrielebelluco.EnergyRoad.payloads.request;

import jakarta.validation.constraints.NotBlank;

public record CreateProductCategoryDTO(
        @NotBlank(message = "il nome della categoria è obbligatorio ")
        String name) {
}
