package com.project.employeemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.employeemanagementsystem.model.Employee;
import com.project.employeemanagementsystem.model.Leave;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	@Query(value = "SELECT * FROM employee WHERE employee_first_name = ?1", nativeQuery = true)
	public List<Employee> serchUserByName(String employee_name);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM leaves WHERE leave_employee_id = ?1")
	void deleteEmployeeLeaves(String employee_id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM login WHERE login_employee_id = ?1")
	void deleteEmployeeLogin(String employee_id);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM salary WHERE salary_employee_id = ?1")
	void deleteEmployeeSalary(String employee_id);

	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM employee, state WHERE state_id = employee_state", nativeQuery = true)
	public List<Employee> serchUserByState(String employee_state);
	

}
