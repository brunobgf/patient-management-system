package com.example.patient_manager.models;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private double height;
    private double weight;
    private String cpf;

    public int calculateAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public double calculateIMC() {
        return this.weight / (this.height * this.height);
    }

    public String getIMCSituation() {
        double imc = this.calculateIMC();
        if (imc < 17)
            return "Muito abaixo do peso";
        if (imc < 18.5)
            return "Abaixo do peso";
        if (imc < 25)
            return "Peso normal";
        if (imc < 30)
            return "Acima do peso";
        if (imc < 35)
            return "Obesidade I";
        if (imc < 40)
            return "Obesidade II (severa)";
        return "Obesidade III (mórbida)";
    }

    public double getIdealWeight() {
        if (this.gender.equalsIgnoreCase("male")) {
            return (72.7 * this.height) - 58;
        } else {
            return (62.1 * this.height) - 44.7;
        }
    }

    public String getMaskedCpf() {
        return "***." + this.cpf.substring(3, 6) + ".***-**";
    }
}