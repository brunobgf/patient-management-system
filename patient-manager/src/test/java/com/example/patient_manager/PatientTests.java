package com.example.patient_manager;

import org.junit.jupiter.api.Test;
import com.example.patient_manager.models.Patient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTests {

    @Test
    void testGetIdealWeightMale() {
        Patient patient = new Patient();
        patient.setHeight(1.75);
        patient.setGender("male");
        assertEquals(69.225, patient.getIdealWeight(), 0.01);
    }

    @Test
    void testGetIdealWeightFemale() {
        Patient patient = new Patient();
        patient.setHeight(1.60);
        patient.setGender("female");
        assertEquals(54.66, patient.getIdealWeight(), 0.01);
    }

    @Test
    void testCalculateIMC() {
        Patient patient = new Patient();
        patient.setHeight(1.75);
        patient.setWeight(70);
        assertEquals(22.86, patient.getBMI(), 0.01);
    }

    @Test
    void testCalculateAge() {
        Patient patient = new Patient();
        patient.setBirthDate(LocalDate.of(1990, 1, 1));
        assertEquals(34, patient.calculateAge());
    }

    @Test
    void testValidateCpf() {
        Patient patient = new Patient();
        patient.setCpf("12345678909");
        assertTrue(patient.validateCpf());
    }

    @Test
    void testGetMaskedCpf() {
        Patient patient = new Patient();
        patient.setCpf("12345678909");
        assertEquals("***.456.***-**", patient.getMaskedCpf());
    }
}