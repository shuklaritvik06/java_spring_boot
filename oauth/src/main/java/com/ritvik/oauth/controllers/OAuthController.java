package com.ritvik.oauth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class OAuthController {
    @GetMapping("/user-github")
    public Map<String,Object> github(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return Collections.singletonMap("name",oAuth2User.getAttribute("name"));
    }
    @GetMapping("/error")
    public String error(HttpServletRequest request){
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }
    @GetMapping("/user-google")
    public Map<String,Object> google(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return Collections.singletonMap("name",oAuth2User.getAttribute("name"));
    }
}