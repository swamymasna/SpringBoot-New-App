package com.swamy.service;

import java.util.List;

import com.swamy.bindings.EmployeeForm;

public interface IEmployeeService {

	public String saveEmployee(EmployeeForm form);
	
	public List<EmployeeForm>getEmployeesList();
	
	public EmployeeForm editEmployee(Integer id);
	
	public List<EmployeeForm> deleteEmployee(Integer id);
}
