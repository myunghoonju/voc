package teamfreash.vocRefund.web.voc;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamfreash.vocRefund.domain.voc.VocService;
import teamfreash.vocRefund.web.voc.dto.VocRequestDto;
import teamfreash.vocRefund.web.voc.dto.VocResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/voc")
@RestController
public class VocController {

    private final VocService vocService;

    @GetMapping("/list")
    public ResponseEntity<List<VocResponseDto>> vocList() {
        List<VocResponseDto> list = vocService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody VocRequestDto voc) {
        Long vocId = vocService.save(voc);
        return new ResponseEntity<>(vocId, HttpStatus.OK);
    }
}
