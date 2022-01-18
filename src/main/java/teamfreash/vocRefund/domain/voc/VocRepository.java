package teamfreash.vocRefund.domain.voc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VocRepository extends JpaRepository<Voc, Long>, VocRepositoryCustom {

    Optional<Voc> findById(long id);

}
