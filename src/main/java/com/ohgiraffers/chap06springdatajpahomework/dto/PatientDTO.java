package com.ohgiraffers.chap06springdatajpahomework.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {
    private int patientCode;
    private String patientName;
    private int patientAge;
    private String patientGender;
    private String patientPhone;
    private String patientEmail;
    private String patientSick;
    private String patientAdmission;
    private int departmentCode;
}
