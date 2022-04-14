package com.swamy.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeForm {

	private Integer empId;
	private String empName;
	private Double empSal;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}
