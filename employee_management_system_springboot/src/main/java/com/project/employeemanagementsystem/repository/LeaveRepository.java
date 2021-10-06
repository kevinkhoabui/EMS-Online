package com.project.employeemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.employeemanagementsystem.model.Leave;
import com.project.employeemanagementsystem.model.Login;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
	
	@Query(value = "SELECT sum(leave_total_days) as total_leaves FROM leaves WHERE leave_employee_id = ?1", nativeQuery = true)
	public Integer getLeavesCount(String employee_id);

}
