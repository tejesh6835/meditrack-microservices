package org.meditrack.authserver.auth_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    private String fullName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String address;
    private String specialization;

    @NotBlank
    private String role;
    private String status;
}
