package com.swamy.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE_TABLE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYEE_ID")
	private Integer empId;
	
	@Column(name = "EMPLOYEE_NAME")
	private String empName;
	
	@Column(name = "EMPLOYEE_SAL")
	private Double empSal;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATED_DATE")
	private LocalDate updatedDate;
}
