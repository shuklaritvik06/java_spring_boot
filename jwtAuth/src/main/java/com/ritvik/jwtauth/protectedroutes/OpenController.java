package com.ritvik.jwtauth.protectedroutes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OpenController {
    @GetMapping("/dashboard")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from dashboard");
    }
}
