package com.ohgiraffers.chap06springdatajpahomework.controller;

import com.ohgiraffers.chap06springdatajpahomework.common.page.Pagenation;
import com.ohgiraffers.chap06springdatajpahomework.common.page.PagingButton;
import com.ohgiraffers.chap06springdatajpahomework.dto.DepartmentDTO;
import com.ohgiraffers.chap06springdatajpahomework.dto.PatientDTO;
import com.ohgiraffers.chap06springdatajpahomework.entity.Patient;
import com.ohgiraffers.chap06springdatajpahomework.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @GetMapping("/{patientCode}")
    public String findPatientById(@PathVariable int patientCode,
                                  Model model){
        PatientDTO patient = patientService.findPatientById(patientCode);
        model.addAttribute("patient",patient);
        return "patient/detail";
    }
    @GetMapping("/list")
    public void findAllPatient(@PageableDefault Pageable pageable,
                               Model model){
        Page<PatientDTO> patientList = patientService.findAllPatient(pageable);
        PagingButton pagingButton =  Pagenation.getPagingButtonInfo(patientList);
        model.addAttribute("patientList",patientList);
        model.addAttribute("paging",pagingButton);
    }
    @GetMapping("/querymethod")
    public String PatientSelectPage(){
        return "/patient/temporary";
    }
    @PostMapping("/querymethod")
    public void patientSelectPage(@RequestParam Integer patientAge,
                                  Model model){
        Sort sort = Sort.by("patientAge").descending();
        List<PatientDTO> patientList = patientService.findPatientLessThanAge(patientAge,sort);

        model.addAttribute("patientList",patientList);
        model.addAttribute("patientAge",patientAge);
    }
    @GetMapping("/insert")
    public void insertPage(){

    }
    @PostMapping("/insert")
    public String insertPatient(@ModelAttribute PatientDTO patientDTO){
        patientService.registPatient(patientDTO);
        return "redirect:/patient/list";
    }
    @GetMapping("/department")
    @ResponseBody
    public List<DepartmentDTO> getDepartment(){
        List<DepartmentDTO> departmentList = patientService.findAllDepartment();
        return departmentList;
    }
    @GetMapping("/modify")
    public void modifyPage(){

    }
    @PostMapping("/modify")
    public String modifyPatient(@ModelAttribute PatientDTO patientDTO){
        patientService.modifyPatient(patientDTO);
        return "redirect:/patient/list";
    }
    @GetMapping("/delete")
    public void deletePage(){

    }
    @PostMapping("/delete")
    public String deletePatient(@RequestParam int patientCode){
        patientService.deletePatient(patientCode);
        return "redirect:/patient/list";
    }

}
