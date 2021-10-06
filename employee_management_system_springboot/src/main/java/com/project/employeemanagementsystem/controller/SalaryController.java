package com.project.employeemanagementsystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.nio.file.Files;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.employeemanagementsystem.exception.ResourceNotFoundException;
import com.project.employeemanagementsystem.model.Employee;
import com.project.employeemanagementsystem.model.Salary;
import com.project.employeemanagementsystem.model.Month;

import com.project.employeemanagementsystem.repository.SalaryRepository;
import com.project.employeemanagementsystem.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class SalaryController {
	private static final String uploadDir = "/Users/kaushalkishore/uploads/";

	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/salaries")
	public List<Salary> getAllSalarys() {
		return salaryRepository.findAll();
	}

	@GetMapping("/salaries/search/{name}")
	public List<Salary> getSalaryByName(@PathVariable(value = "name") String salaryName) {
			return salaryRepository.serchUserByName(salaryName);
	}
	
	@GetMapping("/salaries/search-state/{state}")
	public List<Salary> serchUserByState(@PathVariable(value = "state") String salaryState) {
			return salaryRepository.serchUserByState(salaryState);
	}
	
	@GetMapping("/salaries/all-salaries/{id}")
	public ArrayList getAllSalaryFields(@PathVariable(value = "id") String employee_id) {
		
		String SQL = "SELECT sal, emp, mon from salary sal, employee emp, month mon WHERE month_id = salary_month AND salary_employee_id = employee_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!employee_id.equals("0")) {
			 System.out.print("Employee Id : "+employee_id);

			 SQL = "SELECT sal, emp, mon from salary sal, employee emp, month mon WHERE month_id = salary_month AND salary_employee_id = employee_id AND employee_id = :employee_id";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("employee_id", employee_id);
		 } 
		 List<Object[]> salary = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : salary ) {
			  Employee employee_details = (Employee)row[ 1 ];
			  Salary salary_details = (Salary)row[ 0 ];
			  Month month_details = (Month)row[ 2 ];
			
			    HashMap<String, String> results = new HashMap();
			    results.put("salary_id",String.valueOf(salary_details.getSalary_id()));
			    results.put("month_name",String.valueOf(month_details.getMonth_name()));
			    results.put("employee_id",String.valueOf(employee_details.getEmployee_id()));
				results.put("employee_sal",employee_details.getEmployee_sal());
				results.put("employee_first_name",employee_details.getEmployee_first_name());
				results.put("employee_middle_name",employee_details.getEmployee_middle_name());
				results.put("employee_last_name",employee_details.getEmployee_last_name());
				results.put("employee_gender",employee_details.getEmployee_gender());
				
				results.put("salary_employee_id",String.valueOf(salary_details.getSalary_employee_id()));
				results.put("salary_month",String.valueOf(salary_details.getSalary_month()));
				results.put("salary_working_days",String.valueOf(salary_details.getSalary_working_days()));
				results.put("salary_basic",String.valueOf(salary_details.getSalary_basic()));
				results.put("salary_hra",String.valueOf(salary_details.getSalary_hra()));
				results.put("salary_mediclaim",String.valueOf(salary_details.getSalary_mediclaim()));
				results.put("salary_ta",String.valueOf(salary_details.getSalary_ta()));
				results.put("salary_da",String.valueOf(salary_details.getSalary_da()));
				results.put("salary_reimbursement",String.valueOf(salary_details.getSalary_reimbursement()));
				results.put("salary_ca",String.valueOf(salary_details.getSalary_ca()));
				results.put("salary_others",String.valueOf(salary_details.getSalary_others()));
				results.put("salary_dpf",String.valueOf(salary_details.getSalary_dpf()));
				results.put("salary_dtax",String.valueOf(salary_details.getSalary_dtax()));
				results.put("salary_desc",String.valueOf(salary_details.getSalary_desc()));
				results.put("salary_total",String.valueOf(salary_details.getSalary_total()));
				results.put("salary_dedc",String.valueOf(salary_details.getSalary_dedc()));
				results.put("salary_slip",salary_details.getSalary_slip_filename());
				
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	@GetMapping("/salaries/{id}")
	public ResponseEntity<Salary> getSalaryById(@PathVariable(value = "id") Long salaryId)
			throws ResourceNotFoundException {
		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not found for this id :: " + salaryId));
		return ResponseEntity.ok().body(salary);
	}
	

	@RequestMapping(value = "/salaries", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Salary createSalary(@RequestParam("salary_slip") MultipartFile salary_slip, 
			@ModelAttribute("form") Salary salary) {
		System.out.print("File Data");
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			String fileName = unixTime+"_" +salary_slip.getOriginalFilename();
			this.fileService.uploadToLocalFileSystem(salary_slip, fileName);
            salary.setSalary_slip_filename(fileName);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return salaryRepository.save(salary);
	}
	
	 
    // For Downloading Files
    @GetMapping("/salaries/salary_slip/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable(name = "fileName") String fileName) throws IOException {
    	
    	Path fileLocation = this.fileService.getFileLocation(fileName);
    	File file = new File(fileLocation.toString());
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation.toString()));
   
    	System.out.print("File Name : "+file.getName());
    	// Setting up header for Downloading files ////
    	
    	
    	HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

    	
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @RequestMapping(value = "/salaries/{id}", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Salary updateSalary(@RequestParam("salary_slip") MultipartFile salary_slip, 
			@ModelAttribute("form") Salary salary) {
		System.out.print("File Data");
		try {
			if(!salary_slip.isEmpty()) {
				long unixTime = System.currentTimeMillis() / 1000L;
				String fileName = unixTime+"_" +salary_slip.getOriginalFilename();
				this.fileService.uploadToLocalFileSystem(salary_slip, fileName);
	            salary.setSalary_slip_filename(fileName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return salaryRepository.save(salary);
	}
    
    @PutMapping("/salaries/{id}")
	public ResponseEntity<Salary> updateSalary(@PathVariable(value = "id") Long salaryId,
			@Valid @RequestBody Salary salaryDetails) throws ResourceNotFoundException {
		final Salary updatedSalary = salaryRepository.save(salaryDetails);
		return ResponseEntity.ok(updatedSalary);
	}

	@DeleteMapping("/salaries/{id}")
	public Map<String, Boolean> deleteSalary(@PathVariable(value = "id") Long salaryId)
			throws ResourceNotFoundException {
		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not found for this id :: " + salaryId));

		salaryRepository.delete(salary);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
