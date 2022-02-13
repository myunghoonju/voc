package teamfreash.vocRefund.domain.voc;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import teamfreash.vocRefund.web.voc.dto.VocResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

import static teamfreash.vocRefund.domain.penalty.QPenalty.penalty;
import static teamfreash.vocRefund.domain.voc.QVoc.voc;

public class VocRepositoryImpl implements VocRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;

    public VocRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<VocResponseDto> getList() {
        return queryFactory
                .select(defaultColumn())
                .from(voc)
                .join(penalty)
                .on(voc.id.eq(penalty.voc.id))
                .fetch();
    }

    private QBean<VocResponseDto> defaultColumn() {
        return Projections.fields(
                VocResponseDto.class,
                voc.receiver,
                voc.description,
                penalty.accident,
                penalty.confirm_yn,
                penalty.claim_yn,
                penalty.charge_amt,
                penalty.status);
    }
}
