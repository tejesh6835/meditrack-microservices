package org.meditrack.userservice.dto;

import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisteredUserRequestDto {

    private String username;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String address;
    private String specialization;
    private String role;
    private String status;

}
