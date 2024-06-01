package com.example.patient_manager.models;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

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
    @Transient
    private Double bmi;

    public int calculateAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    public double getBMI() {
        return this.weight / (this.height * this.height);
    }

    public String getBMISituation() {
        double bmi = this.getBMI();
        if (bmi < 17)
            return "Muito abaixo do peso";
        if (bmi < 18.5)
            return "Abaixo do peso";
        if (bmi < 25)
            return "Peso normal";
        if (bmi < 30)
            return "Acima do peso";
        if (bmi < 35)
            return "Obesidade I";
        if (bmi < 40)
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

    public boolean validateCpf() {
        if (this.cpf == null || this.cpf.length() != 11) {
            return false;
        }
        try {
            int[] cpfArray = this.cpf.chars().map(Character::getNumericValue).toArray();
            int v1 = 0, v2 = 0;
            for (int i = 0; i < 9; i++) {
                v1 += cpfArray[i] * (10 - i);
                v2 += cpfArray[i] * (11 - i);
            }
            v1 = (v1 % 11) < 2 ? 0 : 11 - (v1 % 11);
            v2 += v1 * 9;
            v2 = (v2 % 11) < 2 ? 0 : 11 - (v2 % 11);

            return v1 == cpfArray[9] && v2 == cpfArray[10];
        } catch (Exception e) {
            return false;
        }
    }
}