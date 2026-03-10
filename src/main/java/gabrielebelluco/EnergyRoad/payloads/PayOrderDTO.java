package gabrielebelluco.EnergyRoad.payloads;

import jakarta.validation.constraints.NotBlank;

public record PayOrderDTO(
        @NotBlank(message = "il nonce è obbligatorio")
        String paymentMethodNonce
) {
}
