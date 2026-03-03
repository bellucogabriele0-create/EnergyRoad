package gabrielebelluco.EnergyRoad.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email non è nel formato corretto")
    private String email;
    @NotBlank(message = "La password è obbligatoria")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
