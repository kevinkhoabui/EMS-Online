package com.project.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.employeemanagementsystem.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
