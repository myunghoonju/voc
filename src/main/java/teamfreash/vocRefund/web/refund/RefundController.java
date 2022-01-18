package teamfreash.vocRefund.web.refund;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamfreash.vocRefund.domain.refund.Refund;
import teamfreash.vocRefund.domain.refund.RefundService;
import teamfreash.vocRefund.web.refund.dto.RefundRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/refund")
@RestController
public class RefundController {

    private final RefundService refundService;

    @GetMapping("/list")
    public ResponseEntity<List<Refund>> refundList() {
        List<Refund> refundList = refundService.getList();
        return new ResponseEntity<>(refundList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody RefundRequestDto refund) {
        long refundId = refundService.save(refund);
        if (refundId > 0) {
            return new ResponseEntity<>(refundId, HttpStatus.OK);
        }

        return new ResponseEntity<>(refundId, HttpStatus.BAD_REQUEST);
    }
}
