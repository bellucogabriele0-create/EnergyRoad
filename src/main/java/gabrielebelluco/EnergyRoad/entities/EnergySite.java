package gabrielebelluco.EnergyRoad.entities;

import gabrielebelluco.EnergyRoad.enums.EnergySiteStatus;
import gabrielebelluco.EnergyRoad.enums.EnergySiteType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "energy_sites", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"latitude", "longitude"})
})
public class EnergySite {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID energySiteId;
    private String name;
    private String description;
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;
    @Enumerated(EnumType.STRING)
    private EnergySiteType type;
    @Enumerated(EnumType.STRING)
    private EnergySiteStatus status;
    private String image;
    private LocalDateTime createdAt;

    public EnergySite() {
    }

    public EnergySite(String description, String name, BigDecimal latitude, BigDecimal longitude, EnergySiteType type, EnergySiteStatus status, String image) {
        this.description = description;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.status = status;
        this.image = image;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getEnergySiteId() {
        return energySiteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public EnergySiteType getType() {
        return type;
    }

    public void setType(EnergySiteType type) {
        this.type = type;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public EnergySiteStatus getStatus() {
        return status;
    }

    public void setStatus(EnergySiteStatus status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EnergySite{" +
                "energySiteId=" + energySiteId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", type=" + type +
                ", status=" + status +
                ", Image='" + image + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
