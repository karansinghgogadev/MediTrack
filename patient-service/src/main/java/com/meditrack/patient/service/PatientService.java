package com.meditrack.patient.service;

import com.meditrack.patient.dto.request.PatientRequest;
import com.meditrack.patient.dto.response.PatientResponse;
import com.meditrack.patient.entity.Patient;
import com.meditrack.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientResponse createProfile(PatientRequest request) {
        if (patientRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Profile already exists!");
        }

        Patient patient = Patient.builder()
                .userId(request.getUserId())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .bloodGroup(request.getBloodGroup())
                .phone(request.getPhone())
                .address(request.getAddress())
                .emergencyContact(request.getEmergencyContact())
                .build();

        return mapToResponse(patientRepository.save(patient));
    }

    public PatientResponse getProfileByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Patient profile not found!"));
        return mapToResponse(patient);
    }

    public PatientResponse updateProfile(Long userId,
                                         PatientRequest request) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Patient profile not found!"));

        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setPhone(request.getPhone());
        patient.setAddress(request.getAddress());
        patient.setEmergencyContact(request.getEmergencyContact());

        return mapToResponse(patientRepository.save(patient));
    }

    public PatientResponse getById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Patient not found!"));
        return mapToResponse(patient);
    }

    public Page<PatientResponse> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private PatientResponse mapToResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .userId(patient.getUserId())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .bloodGroup(patient.getBloodGroup())
                .phone(patient.getPhone())
                .address(patient.getAddress())
                .emergencyContact(patient.getEmergencyContact())
                .createdAt(patient.getCreatedAt())
                .build();
    }
}