package info.michaelmogessie.scheduler.configs;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.HypermediaWebClientConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    WebClient.Builder webClientBuilder(HypermediaWebClientConfigurer configurer) {
        return configurer.registerHypermediaTypes(WebClient.builder());
    }

    @Bean
    WebClientCustomizer webClientCustomizer(HypermediaWebClientConfigurer configurer) {
        return webClientBuilder -> {
            configurer.registerHypermediaTypes(webClientBuilder);
        };
    }
}
