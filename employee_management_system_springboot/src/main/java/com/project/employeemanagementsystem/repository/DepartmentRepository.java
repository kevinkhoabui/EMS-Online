package com.project.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.employeemanagementsystem.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
