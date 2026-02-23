package com.meditrack.patient.controller;

import com.meditrack.patient.dto.request.PatientRequest;
import com.meditrack.patient.dto.response.*;
import com.meditrack.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/profile")
    public ResponseEntity<ApiResponse<PatientResponse>> createProfile(
            @Valid @RequestBody PatientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        patientService.createProfile(request)));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<PatientResponse>> getProfile(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        patientService.getProfileByUserId(userId)));
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<PatientResponse>> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody PatientRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        patientService.updateProfile(userId, request)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<ApiResponse<PatientResponse>> getPatientById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(patientService.getById(id)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<PatientResponse>>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        patientService.getAllPatients(
                                PageRequest.of(page, size))));
    }
}
