package org.meditrack.authserver.auth_server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.meditrack.authserver.auth_server.dto.RegisterRequest;
import org.meditrack.authserver.auth_server.dto.RegisterRequestToUserDto;
import org.meditrack.authserver.auth_server.entity.AuthUser;
import org.meditrack.authserver.auth_server.entity.Role;
import org.meditrack.authserver.auth_server.exception.UserAlreadyExistsException;
import org.meditrack.authserver.auth_server.feign.UserClient;
import org.meditrack.authserver.auth_server.mapper.RegisterMapper;
import org.meditrack.authserver.auth_server.repository.AuthUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder encoder;
    private final UserClient userClient;

    @Transactional
    public void register(RegisterRequest request) {

        if (repository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        AuthUser user = new AuthUser();
        user.setEnabled(true);
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));

        repository.save(user);

        RegisterRequestToUserDto registerRequestToUserDto =
                RegisterMapper.toUserDto(request);

        userClient.saveRegisteredUserFromAuth(registerRequestToUserDto);
    }
}
