package gabrielebelluco.EnergyRoad.entities;

import gabrielebelluco.EnergyRoad.ENUMS.InvestmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID investmentId;
    private Long amount;
    private LocalDate investmentDate;
    @Enumerated(EnumType.STRING)
    private InvestmentStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "energy_site_id")
    private EnergySite energySite;

    public Investment() {
    }

    public Investment(UUID investmentId, Long amount, LocalDate investmentDate, InvestmentStatus status, User user, EnergySite energySite) {
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(LocalDate investmentDate) {
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
