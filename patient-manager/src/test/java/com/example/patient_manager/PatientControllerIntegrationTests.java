package com.example.patient_manager;

import com.example.patient_manager.controller.PatientController;
import com.example.patient_manager.models.Patient;
import com.example.patient_manager.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
class PatientControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientRepository patientRepository;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new Patient(null, "John Doe", "male", LocalDate.of(1990, 1, 1), 1.75, 70.0, "12345678909", null);
    }

    @Test
    void testGetAllPatients() throws Exception {
        when(patientRepository.findAll()).thenReturn(Collections.singletonList(patient));

        mockMvc.perform(get("/api/v1/patients/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testCreatePatient() throws Exception {
        when(patientRepository.save(patient)).thenReturn(patient);

        mockMvc.perform(post("/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"name\":\"John Doe\",\"gender\":\"male\",\"birthDate\":\"1990-01-01\",\"height\":1.75,\"weight\":70.0,\"cpf\":\"12345678909\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetPatientById() throws Exception {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/api/v1/patients/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdatePatient() throws Exception {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);

        mockMvc.perform(put("/api/v1/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"name\":\"John Doe Updated\",\"gender\":\"male\",\"birthDate\":\"1990-01-01\",\"height\":1.75,\"weight\":70.0,\"cpf\":\"12345678909\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe Updated"));
    }

    @Test
    void testDeletePatient() throws Exception {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(delete("/api/v1/patients/1"))
                .andExpect(status().isOk());
    }
}