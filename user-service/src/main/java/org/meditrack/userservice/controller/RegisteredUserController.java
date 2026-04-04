package org.meditrack.userservice.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.meditrack.userservice.dto.RegisteredUserRequestDto;
import org.meditrack.userservice.entity.RegisteredUser;
import org.meditrack.userservice.service.RegisteredUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    @PostMapping("/api/registered-user")
    @PreAuthorize("hasAuthority('SCOPE_internal.write')")
    public ResponseEntity<@NonNull RegisteredUserRequestDto> saveRegisteredUserFromAuth(@RequestBody RegisteredUserRequestDto registeredUserRequestDto) {
        return ResponseEntity.ok(registeredUserService.saveRegisteredUserFromAuth(registeredUserRequestDto));
    }

    @GetMapping("/api/registered-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<@NonNull List<RegisteredUser>> getAllRegisteredUsers() {
        return ResponseEntity.ok(registeredUserService.getAllRegisteredUsers());
    }

    @GetMapping("/debug")
    public Object debug(Authentication authentication) {
        return authentication.getAuthorities();
    }

}
