package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.exceptions.UnauthorizedException;
import gabrielebelluco.EnergyRoad.payloads.request.LoginDTO;
import gabrielebelluco.EnergyRoad.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginDTO payload) {
        User user = userService.findByEmail(payload.getEmail());
        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Credenziali non valide");
        }
        return jwtUtils.generateToken(user);
    }
}
