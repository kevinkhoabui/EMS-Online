package com.project.employeemanagementsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employeemanagementsystem.exception.ResourceNotFoundException;
import com.project.employeemanagementsystem.model.Country;
import com.project.employeemanagementsystem.model.Department;
import com.project.employeemanagementsystem.model.Employee;
import com.project.employeemanagementsystem.model.State;
import com.project.employeemanagementsystem.repository.EmployeeRepository;
import com.project.employeemanagementsystem.repository.LeaveRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/search/{name}")
	public List<Employee> getEmployeeByName(@PathVariable(value = "name") String employeeName) {
			return employeeRepository.serchUserByName(employeeName);
	}
	
	@GetMapping("/employees/search-state/{state}")
	public List<Employee> serchUserByState(@PathVariable(value = "state") String employeeState) {
			return employeeRepository.serchUserByState(employeeState);
	}
	
	@GetMapping("/employees/all-employees")
	public ArrayList getAllEmployeeFields() {
		 Query q = entityManager.createQuery("SELECT emp, cn, dn from employee emp, country cn, department dn  WHERE employee_department = department_id AND employee_country = country_id");
		 List<Object[]> employee = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : employee ) {
			  Employee employee_details = (Employee)row[ 0 ];
			  Country country_details = (Country)row[ 1 ];
			  Department department_details = (Department)row[ 2 ];
			  
			    HashMap<String, String> results = new HashMap();
			    String totalLeaves = String.valueOf(leaveRepository.getLeavesCount(String.valueOf(employee_details.getEmployee_id())));
			    if(totalLeaves == "null") {
			    	totalLeaves = "No Leaves";
			    } else {
			    	totalLeaves=totalLeaves+" Leaves";
			    }
			    results.put("department_name",department_details.getDepartment_name());
			    results.put("country_name",country_details.getCountry_name());
			    results.put("employee_id",String.valueOf(employee_details.getEmployee_id()));
				results.put("employee_sal",employee_details.getEmployee_sal());
				results.put("employee_first_name",employee_details.getEmployee_first_name());
				results.put("employee_middle_name",employee_details.getEmployee_middle_name());
				results.put("employee_last_name",employee_details.getEmployee_last_name());
				results.put("employee_gender",employee_details.getEmployee_gender());
				results.put("employee_address",employee_details.getEmployee_address());
				results.put("employee_village",employee_details.getEmployee_village());
				results.put("employee_state",employee_details.getEmployee_state());
				results.put("employee_country",employee_details.getEmployee_country());
				results.put("employee_landline",employee_details.getEmployee_landline());
				results.put("employee_mobile",employee_details.getEmployee_mobile());
				results.put("employee_email",employee_details.getEmployee_email());
				results.put("employee_status",employee_details.getEmployee_status());
				results.put("employee_deparment",employee_details.getemployee_department());
				results.put("employee_dob",employee_details.getEmployee_dob());
				results.put("employee_nationalty",employee_details.getEmployee_nationalty());
				results.put("employee_leaves",totalLeaves);
				
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
//	@GetMapping("/employees/all-employees")
//	public ArrayList getAllEmployeeFields() {
//		 Query q = entityManager.createQuery("SELECT emp, st, cn, dn from employee emp, state st, country cn, department dn  WHERE employee_state = state_id AND employee_department = department_id AND employee_country = country_id");
//		 List<Object[]> employee = q.getResultList();
//		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
//		 
//		 for ( Object[] row : employee ) {
//			  State state_details = (State)row[ 1 ];
//			  Employee employee_details = (Employee)row[ 0 ];
//			  Country country_details = (Country)row[ 2 ];
//			  Department department_details = (Department)row[ 3 ];
//			  
//			    HashMap<String, String> results = new HashMap();
//			    results.put("state_name",state_details.getState_name());
//			    results.put("department_name",department_details.getDepartment_name());
//			    results.put("country_name",country_details.getCountry_name());
//			    results.put("employee_id",String.valueOf(employee_details.getEmployee_id()));
//				results.put("employee_sal",employee_details.getEmployee_sal());
//				results.put("employee_first_name",employee_details.getEmployee_first_name());
//				results.put("employee_middle_name",employee_details.getEmployee_middle_name());
//				results.put("employee_last_name",employee_details.getEmployee_last_name());
//				results.put("employee_gender",employee_details.getEmployee_gender());
//				results.put("employee_address",employee_details.getEmployee_address());
//				results.put("employee_village",employee_details.getEmployee_village());
//				results.put("employee_state",employee_details.getEmployee_state());
//				results.put("employee_country",employee_details.getEmployee_country());
//				results.put("employee_landline",employee_details.getEmployee_landline());
//				results.put("employee_mobile",employee_details.getEmployee_mobile());
//				results.put("employee_email",employee_details.getEmployee_email());
//				results.put("employee_status",employee_details.getEmployee_status());
//				results.put("employee_deparment",employee_details.getemployee_department());
//				results.put("employee_dob",employee_details.getEmployee_dob());
//				results.put("employee_nationalty",employee_details.getEmployee_nationalty());
//			    resultArray.add(results);
//			 
//		 }	 
//
//        return resultArray;
//	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		final Employee updatedEmployee = employeeRepository.save(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employeeRepository.delete(employee);
		employeeRepository.deleteEmployeeLeaves(employeeId.toString());
		employeeRepository.deleteEmployeeLogin(employeeId.toString());
		employeeRepository.deleteEmployeeSalary(employeeId.toString());
		
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
