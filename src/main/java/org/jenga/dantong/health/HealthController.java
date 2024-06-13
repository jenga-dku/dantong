package org.jenga.dantong.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthController {

    @GetMapping()
    public String healthcheck() {
        return "OK";
    }
}
