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
import com.project.employeemanagementsystem.model.Employee;
import com.project.employeemanagementsystem.model.Leave;
import com.project.employeemanagementsystem.model.Login;
import com.project.employeemanagementsystem.repository.LeaveRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LeaveController {
	@Autowired
	private LeaveRepository leaveRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/leaves")
	public List<Leave> getAllLeaves() {
		return leaveRepository.findAll();
	}
	
	@GetMapping("/leaves/all-leaves/{id}")
	public ArrayList getAllLeaveFields(@PathVariable(value = "id") String employee_id) {
			
		 String SQL = "SELECT emp, lv from leaves lv, employee emp WHERE employee_id = leave_employee_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!employee_id.equals("0")) {
			 System.out.print("Employee Id : "+employee_id);

			 SQL = "SELECT emp, lv from leaves lv, employee emp WHERE employee_id = leave_employee_id AND employee_id = :employee_id";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("employee_id", employee_id);
		 } 
		 List<Object[]> leave = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : leave ) {
			  Leave leave_details = (Leave)row[ 1 ];
			  Employee employee_details = (Employee)row[ 0 ];
			  
			    HashMap<String, String> results = new HashMap();
			    results.put("leave_id",String.valueOf(leave_details.getLeave_id()));
				results.put("leave_reason",leave_details.getLeave_reason());
				results.put("leave_description",leave_details.getLeave_description());
				results.put("leave_from_date",leave_details.getLeave_from_date());
				results.put("leave_to_date",leave_details.getLeave_to_date());
				results.put("leave_status",leave_details.getLeave_status());
				results.put("leave_total_days",leave_details.getLeave_total_days());
				
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
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	@GetMapping("/leaves/{id}")
	public ResponseEntity<Leave> getEmployeeById(@PathVariable(value = "id") Long leaveId)
			throws ResourceNotFoundException {
		Leave leave = leaveRepository.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
		return ResponseEntity.ok().body(leave);
	}

	@PostMapping("/leaves")
	public Leave createLeave(@Valid @RequestBody Leave leave) {
		return leaveRepository.save(leave);
	}
	
	@GetMapping("/leaves/get-leaves-count/{employee_id}")
	public int checkUserNameExits(@PathVariable(value = "employee_id") String employee_id) {
		Integer data = leaveRepository.getLeavesCount(employee_id);
		System.out.print(data);
		return data;
		
	}

	@PutMapping("/leaves/{id}")
	public ResponseEntity<Leave> updateLeave(@PathVariable(value = "id") Long leaveId,
			@Valid @RequestBody Leave leaveDetails) throws ResourceNotFoundException {
		final Leave updatedLeave = leaveRepository.save(leaveDetails);
		return ResponseEntity.ok(updatedLeave);
	}

	@DeleteMapping("/leaves/{id}")
	public Map<String, Boolean> deleteLeave(@PathVariable(value = "id") Long leaveId)
			throws ResourceNotFoundException {
		Leave leave = leaveRepository.findById(leaveId)
				.orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));

		leaveRepository.delete(leave);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
