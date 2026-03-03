package gabrielebelluco.EnergyRoad.Repositories;

import gabrielebelluco.EnergyRoad.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
    boolean existsById(UUID id);

    boolean existsByTitle(String title);

    List<News> findAllByOrderByNewsCreatedAtDesc(LocalDate newsCreatedAt);

}
