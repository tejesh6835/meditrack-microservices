package org.meditrack.authserver.auth_server.mapper;

import org.meditrack.authserver.auth_server.dto.RegisterRequest;
import org.meditrack.authserver.auth_server.dto.RegisterRequestToUserDto;

public class RegisterMapper {

    public static RegisterRequestToUserDto toUserDto(RegisterRequest request) {

        return RegisterRequestToUserDto.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .dob(request.getDob())
                .gender(request.getGender())
                .address(request.getAddress())
                .specialization(request.getSpecialization())
                .role(request.getRole())
                .status("ACTIVE")
                .build();
    }
}
