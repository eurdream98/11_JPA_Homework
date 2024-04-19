package com.ohgiraffers.chap06springdatajpahomework.repository;

import com.ohgiraffers.chap06springdatajpahomework.common.page.Pagenation;
import com.ohgiraffers.chap06springdatajpahomework.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
//    List<Patient> findByPatientAgeLessThanEqual(Integer patientAge);
//    List<Patient> findByPatientAgeLessThanEqualOrderByPatientAge(Integer patientAge);
    List<Patient> findByPatientAgeLessThanEqual(Integer patientAge, Sort sort);
}
