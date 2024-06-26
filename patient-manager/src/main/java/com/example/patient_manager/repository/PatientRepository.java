package com.example.patient_manager.repository;

import com.example.patient_manager.models.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
