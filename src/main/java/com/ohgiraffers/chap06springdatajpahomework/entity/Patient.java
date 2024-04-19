package com.ohgiraffers.chap06springdatajpahomework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="patient")
@Table(name="tbl_patient")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Patient {
    @Id
    private int patientCode;
    private String patientName;
    private int patientAge;
    private String patientGender;
    private String patientPhone;
    private String patientEmail;
    private String patientSick;
    private String patientAdmission;
    private int departmentCode;

    public void modifyPatientName(String patientName) {
        this.patientName = patientName;
    }
}
