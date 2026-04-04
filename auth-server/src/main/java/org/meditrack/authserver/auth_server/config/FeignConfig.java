package org.meditrack.authserver.auth_server.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

import feign.micrometer.MicrometerObservationCapability;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.meditrack.authserver.auth_server.exception.FeignErrorDecoder;
import org.meditrack.authserver.auth_server.service.InternalTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final InternalTokenService tokenService;
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    public MicrometerObservationCapability micrometerObservationCapability(ObservationRegistry registry) {
        return new MicrometerObservationCapability(registry);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {

            String token = tokenService.getInternalAccessToken();
            System.out.println("TOKEN SENT TO USER SERVICE: " + token);

            if (!template.headers().containsKey("Authorization")) {
                template.header("Authorization", "Bearer " + token);
            }
        };
    }
}
