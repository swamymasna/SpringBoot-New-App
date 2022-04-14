package com.swamy.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swamy.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {

}
