package teamfreash.vocRefund.domain.voc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VocRepository extends JpaRepository<Voc, Long>, VocRepositoryCustom {

}
