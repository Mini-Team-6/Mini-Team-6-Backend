package ybe.mini.travelserver.global;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RemoveThisAfterCommit {

    @GetMapping
    public String hello() {
        return "6조 화이팅!";
    }
}
