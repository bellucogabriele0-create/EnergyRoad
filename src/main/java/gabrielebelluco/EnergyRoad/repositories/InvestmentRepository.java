package gabrielebelluco.EnergyRoad.repositories;

import gabrielebelluco.EnergyRoad.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    boolean existsByInvestmentId(UUID id);

    List<Investment> findByInvestmentDate(LocalDateTime investmentDate);
}
