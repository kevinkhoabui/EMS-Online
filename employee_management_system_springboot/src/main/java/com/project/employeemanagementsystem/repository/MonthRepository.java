package com.project.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.employeemanagementsystem.model.Month;

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {

}
