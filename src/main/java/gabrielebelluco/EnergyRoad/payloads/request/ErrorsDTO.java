package gabrielebelluco.EnergyRoad.payloads.request;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timeStamp) {
}
