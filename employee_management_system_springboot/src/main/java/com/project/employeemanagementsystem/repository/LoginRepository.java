package com.project.employeemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.employeemanagementsystem.model.Employee;
import com.project.employeemanagementsystem.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	
	@Query(value = "SELECT * FROM login WHERE login_email = ?1 AND login_password = ?2", nativeQuery = true)
	public Login checkLogin(String login_email, String login_password);
	
	@Query(value = "SELECT * FROM login WHERE login_email = ?1", nativeQuery = true)
	public List<Login> serchUserByName(String username);
	
	

}
