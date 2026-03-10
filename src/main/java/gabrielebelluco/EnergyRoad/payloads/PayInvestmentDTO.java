package gabrielebelluco.EnergyRoad.payloads;

import jakarta.validation.constraints.NotBlank;

public record PayInvestmentDTO(
        @NotBlank(message = "il nonce è obbligatorio")
        String paymentMethodNonce
) {
}
