package teamfreash.vocRefund.web.penalty;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamfreash.vocRefund.domain.penalty.PenaltyService;
import teamfreash.vocRefund.web.penalty.dto.PenaltyRequestDto;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/penalty")
@RestController
public class PenaltyController {

    private final PenaltyService penaltyService;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody PenaltyRequestDto penalty) {
        long penaltyId = penaltyService.save(penalty);
        return new ResponseEntity<>(penaltyId, HttpStatus.OK);
    }

    @GetMapping("/accept")
    public ResponseEntity<Long> accept(@RequestParam Long vocId) {
        long penaltyId = penaltyService.confirm(vocId);
        return new ResponseEntity<>(penaltyId, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkCondition(@RequestParam Long vocId) {
        boolean result = penaltyService.isRefundable(vocId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
