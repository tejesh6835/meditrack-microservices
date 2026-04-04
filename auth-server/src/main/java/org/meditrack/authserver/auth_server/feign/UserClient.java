package org.meditrack.authserver.auth_server.feign;

import lombok.NonNull;
import org.meditrack.authserver.auth_server.config.FeignConfig;
import org.meditrack.authserver.auth_server.dto.RegisterRequestToUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", configuration= {FeignConfig.class})
public interface UserClient {

    @PostMapping("/api/registered-user")
    public ResponseEntity<@NonNull RegisterRequestToUserDto> saveRegisteredUserFromAuth(@RequestBody RegisterRequestToUserDto registerRequestToUserDto);
}
