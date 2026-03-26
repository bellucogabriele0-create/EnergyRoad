package gabrielebelluco.EnergyRoad.payloads.request;

import jakarta.validation.constraints.NotBlank;

public record PayOrderDTO(
        @NotBlank(message = "il nonce è obbligatorio")
        String paymentMethodNonce
) {
}
