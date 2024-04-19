package com.ohgiraffers.chap06springdatajpahomework.repository;

import com.ohgiraffers.chap06springdatajpahomework.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
//    @Query("SELECT d FROM department d ORDER BY d.departmentCode")
//    List<Department> findAllDepartment();

    @Query(value=("SELECT * FROM tbl_department"),nativeQuery = true)
    List<Department> findAllDepartment();
}
