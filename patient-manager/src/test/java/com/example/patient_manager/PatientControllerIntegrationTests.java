package com.example.patient_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.patient_manager.models.Patient;
import com.example.patient_manager.repository.PatientRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
    }

    @Test
    void testGetAllPatients() {
        patientRepository
                .save(new Patient(null, "John Doe", "male", LocalDate.of(1990, 1, 1), 1.75, 70.0, "12345678909", null));

        String url = "http://localhost:" + port + "/api/v1/patients/all";
        ResponseEntity<Patient[]> response = restTemplate.getForEntity(url, Patient[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(1);
    }

    @Test
    void testCreatePatient() {
        String url = "http://localhost:" + port + "/api/v1/patients";
        Patient patient = new Patient(null, "John Doe", "male", LocalDate.of(1990, 1, 1), 1.75, 70.0, "12345678909",
                null);

        ResponseEntity<Patient> response = restTemplate.postForEntity(url, patient, Patient.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
    }

    @Test
    void testGetPatientById() {
        Patient savedPatient = patientRepository.save(
                new Patient(null, "Jane Doe", "female", LocalDate.of(1992, 2, 2), 1.65, 60.0, "98765432100", null));
        String url = "http://localhost:" + port + "/api/v1/patients/" + savedPatient.getId();
        ResponseEntity<Patient> response = restTemplate.getForEntity(url, Patient.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testUpdatePatient() {
        Patient savedPatient = patientRepository.save(
                new Patient(null, "Jane Doe", "female", LocalDate.of(1992, 2, 2), 1.65, 60.0, "98765432100", null));
        String url = "http://localhost:" + port + "/api/v1/patients/" + savedPatient.getId();

        Patient updatedPatient = new Patient(null, "Jane Doe Updated", "female", LocalDate.of(1992, 2, 2), 1.65, 65.0,
                "98765432100", null);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Patient> entity = new HttpEntity<>(updatedPatient, headers);

        ResponseEntity<Patient> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Patient.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Jane Doe Updated");
        assertThat(response.getBody().getWeight()).isEqualTo(65.0);
    }

    @Test
    void testDeletePatient() {
        Patient savedPatient = patientRepository.save(
                new Patient(null, "Jane Doe", "female", LocalDate.of(1992, 2, 2), 1.65, 60.0, "98765432100", null));
        String url = "http://localhost:" + port + "/api/v1/patients/" + savedPatient.getId();

        restTemplate.delete(url);
        ResponseEntity<Patient> response = restTemplate.getForEntity(url, Patient.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}