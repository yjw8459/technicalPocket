package yjw.technical.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yjw.technical.vo.TestVO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RequestBodyEx {
    /**
     * @RequestBody : RequestResponseBodyMethodProcessor를 통해서 메서드 파라미터로 바인딩된다.
     * @Validated : Controller에서는 @Validated 어노테이션을 붙일 필요가 없다.
     * @Valid : @RequestBody 와 함께 설정하면 바인딩과 함께 유횩값 검증을 한다.
     * @param test
     * @return
     */
    @PostMapping(value = "/request/bodyTest")
    public ResponseEntity<Void> create(@Valid @RequestBody TestVO test){
        return null;
    }

}
