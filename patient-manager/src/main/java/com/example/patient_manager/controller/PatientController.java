package com.example.patient_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patient_manager.models.Patient;
import com.example.patient_manager.repository.PatientRepository;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> newPatient(@RequestBody Patient patient) {
        Patient savedPatient = repository.save(patient);
        return ResponseEntity.status(201).body(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        return repository.findById(id)
                .map(patient -> {
                    patient.setName(patientDetails.getName());
                    patient.setGender(patientDetails.getGender());
                    patient.setBirthDate(patientDetails.getBirthDate());
                    patient.setHeight(patientDetails.getHeight());
                    patient.setWeight(patientDetails.getWeight());
                    patient.setCpf(patientDetails.getCpf());
                    return ResponseEntity.ok(repository.save(patient));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id) {
        return repository.findById(id)
                .map(patient -> {
                    repository.delete(patient);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}