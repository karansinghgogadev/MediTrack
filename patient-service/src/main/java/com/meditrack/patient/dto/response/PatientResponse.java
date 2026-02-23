package com.meditrack.patient.dto.response;

import com.meditrack.patient.entity.Gender;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
    private Long id;
    private Long userId;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String bloodGroup;
    private String phone;
    private String address;
    private String emergencyContact;
    private LocalDateTime createdAt;
}