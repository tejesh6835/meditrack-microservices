package org.meditrack.userservice.service;

import lombok.RequiredArgsConstructor;
import org.meditrack.userservice.dto.RegisteredUserRequestDto;
import org.meditrack.userservice.entity.RegisteredUser;
import org.meditrack.userservice.entity.Role;
import org.meditrack.userservice.entity.Status;
import org.meditrack.userservice.repository.RegisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;


    public RegisteredUserRequestDto saveRegisteredUserFromAuth(RegisteredUserRequestDto registeredUserRequestDto) {

        RegisteredUser user = new RegisteredUser();
        user.setUsername(registeredUserRequestDto.getUsername());
        user.setFullName(registeredUserRequestDto.getFullName());
        user.setEmail(registeredUserRequestDto.getEmail());
        user.setRole(Role.valueOf(registeredUserRequestDto.getRole().toUpperCase()));
        user.setDob(registeredUserRequestDto.getDob());
        user.setAddress(registeredUserRequestDto.getAddress());
        user.setPhone(registeredUserRequestDto.getPhone());
        user.setSpecialization(registeredUserRequestDto.getSpecialization());
        user.setStatus(Status.valueOf(registeredUserRequestDto.getStatus()));
        user.setGender(registeredUserRequestDto.getGender());

        registeredUserRepository.save(user);
        return registeredUserRequestDto;
    }

    public List<RegisteredUser> getAllRegisteredUsers() {
        return registeredUserRepository.findAll();
    }
}
