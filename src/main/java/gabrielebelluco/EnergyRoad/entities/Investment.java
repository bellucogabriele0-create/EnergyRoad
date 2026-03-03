package gabrielebelluco.EnergyRoad.entities;

import gabrielebelluco.EnergyRoad.enums.InvestmentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID investmentId;
    @Column(nullable = false, scale = 2)
    private BigDecimal amount;
    private LocalDateTime investmentDate;
    @Enumerated(EnumType.STRING)
    private InvestmentStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "energy_site_id")
    private EnergySite energySite;

    public Investment() {
    }

    public Investment(UUID investmentId, BigDecimal amount, LocalDateTime investmentDate, InvestmentStatus status, User user, EnergySite energySite) {
        this.investmentId = investmentId;
        this.amount = amount;
        this.investmentDate = investmentDate;
        this.status = status;
        this.user = user;
        this.energySite = energySite;
    }

    public UUID getInvestmentId() {
        return investmentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(LocalDateTime investmentDate) {
        this.investmentDate = investmentDate;
    }

    public InvestmentStatus getStatus() {
        return status;
    }

    public void setStatus(InvestmentStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EnergySite getEnergySite() {
        return energySite;
    }

    public void setEnergySite(EnergySite energySite) {
        this.energySite = energySite;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "investmentId=" + investmentId +
                ", amount=" + amount +
                ", investmentDate=" + investmentDate +
                ", status=" + status +
                ", user=" + user +
                ", energySite=" + energySite +
                '}';
    }
}
