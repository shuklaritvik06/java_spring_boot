package com.ritvik.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class SecurityConfiguration {
    SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().requestMatchers("/index.html","/webjars/**").permitAll().anyRequest().authenticated().and().oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
            try {
                httpSecurityOAuth2LoginConfigurer.failureHandler(((request, response, exception) -> {
                    request.getSession().setAttribute("error.message",exception.getMessage());
                    simpleUrlAuthenticationFailureHandler.onAuthenticationFailure(request,response,exception);
                })).and().logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/").permitAll());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }
}