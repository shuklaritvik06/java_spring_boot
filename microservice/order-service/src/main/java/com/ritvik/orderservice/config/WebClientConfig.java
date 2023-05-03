package com.ritvik.orderservice.config;

import com.ritvik.orderservice.client.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;
    @Bean
    public WebClient paymentWebClient(){
        return WebClient.builder().filter(loadBalancedExchangeFilterFunction).baseUrl("http://order-service").build();
    }
    @Bean
    public PaymentClient  paymentClient(){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(paymentWebClient())).build();
        return httpServiceProxyFactory.createClient(PaymentClient.class);
    }
}
