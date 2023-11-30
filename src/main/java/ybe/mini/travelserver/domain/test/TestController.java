package ybe.mini.travelserver.domain.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    // "test" 응답
    @GetMapping()
    public ResponseDto<String> test(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseDto<>(HttpStatus.OK.value(), "test");
    }

    // 받은 body를 그대로 응답으로 반환
    @GetMapping("/echo")
    public ResponseDto<Object> echoRequestBody(@RequestBody Object requestBody) {
        return new ResponseDto<>(HttpStatus.OK.value(), requestBody);
    }

}