package com.ohgiraffers.chap06springdatajpahomework.service;

import com.ohgiraffers.chap06springdatajpahomework.common.page.Pagenation;
import com.ohgiraffers.chap06springdatajpahomework.dto.DepartmentDTO;
import com.ohgiraffers.chap06springdatajpahomework.dto.PatientDTO;
import com.ohgiraffers.chap06springdatajpahomework.entity.Department;
import com.ohgiraffers.chap06springdatajpahomework.entity.Patient;
import com.ohgiraffers.chap06springdatajpahomework.repository.DepartmentRepository;
import com.ohgiraffers.chap06springdatajpahomework.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public PatientDTO findPatientById(Integer patientCode) {
        Patient patient = patientRepository.findById(patientCode).orElseThrow(IllegalAccessError::new);
        return modelMapper.map(patient,PatientDTO.class);
    }

    public Page<PatientDTO> findAllPatient(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0?0:pageable.getPageNumber()-1,
                pageable.getPageSize(),
                Sort.by("patientCode").descending()
        );
        Page<Patient> pageList = patientRepository.findAll(pageable);
        return pageList.map(patient -> modelMapper.map(patient,PatientDTO.class));
    }

    public List<PatientDTO> findPatientLessThanAge(int patientAge,Sort sort) {
        List<Patient> patientList = patientRepository.findByPatientAgeLessThanEqual(patientAge,sort);
        return patientList.stream().map(patient -> modelMapper.map(patient,PatientDTO.class)).toList();
    }

    public List<DepartmentDTO> findAllDepartment() {
        List<Department> departmentList = departmentRepository.findAllDepartment();
        return departmentList.stream().map(department -> modelMapper.map(department, DepartmentDTO.class)).toList();
    }
@Transactional
    public void registPatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO,Patient.class);
        patientRepository.save(patient);
    }
@Transactional
    public void modifyPatient(PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(patientDTO.getPatientCode()).orElseThrow(IllegalAccessError::new);
        patient.modifyPatientName(patientDTO.getPatientName());
    }
@Transactional
    public void deletePatient(int patientCode) {
        patientRepository.deleteById(patientCode);
    }
}
