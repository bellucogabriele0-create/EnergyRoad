package gabrielebelluco.EnergyRoad.payloads.response;

import gabrielebelluco.EnergyRoad.entities.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record UserResponseDTO(
        UUID userId,
        String firstname,
        String lastname,
        String email,
        String avatarUrl,
        LocalDate createdAt,
        Set<String> roles
) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getAvatarUrl(),
                user.getCreatedAt(),
                user.getRoles().stream()
                        .map(r -> r.getRoleType().name())
                        .collect(Collectors.toSet())
        );
    }
}