package com.project.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.employeemanagementsystem.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
