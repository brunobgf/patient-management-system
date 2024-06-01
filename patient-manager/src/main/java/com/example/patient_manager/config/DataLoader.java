package com.example.patient_manager.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.patient_manager.models.Patient;
import com.example.patient_manager.repository.PatientRepository;

@Configuration
public class DataLoader {

    @Autowired
    private PatientRepository patientRepository;

    @Bean
    CommandLineRunner loadData() {
        return args -> {
            // Insert initial data
            Patient patient1 = new Patient();
            patient1.setName("John Doe");
            patient1.setGender("Male");
            patient1.setBirthDate(LocalDate.of(1985, 5, 15));
            patient1.setHeight(1.75);
            patient1.setWeight(75.0);
            patient1.setCpf("123.456.789-10");

            patientRepository.save(patient1);
        };
    }
}
