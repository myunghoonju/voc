package teamfreash.vocRefund.domain.penalty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    @Query("SELECT p FROM Penalty p WHERE p.voc.id = :voc_id")
    Penalty findByVocId(@Param("voc_id") long voc_id);
}
