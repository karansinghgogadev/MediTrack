package com.meditrack.patient.dto.request;

import com.meditrack.patient.entity.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    private LocalDate dateOfBirth;

    private Gender gender;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$",
             message = "Invalid blood group")
    private String bloodGroup;

    @Pattern(regexp = "^[0-9]{10}$",
             message = "Phone must be 10 digits")
    private String phone;

    private String address;

    private String emergencyContact;
}