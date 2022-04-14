package com.swamy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swamy.bindings.EmployeeForm;
import com.swamy.entity.Employee;
import com.swamy.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(EmployeeForm form) {

		Employee entity = new Employee();
		BeanUtils.copyProperties(form, entity);
		Employee employee = employeeRepository.save(entity);

		if(employee.getEmpId() != null) {
			return "Employee Data '"+employee.getEmpId()+"' Saved Successfully";
		}
		else {
			return "Employee Data Not Saved..?";
		}
	}

	@Override
	public List<EmployeeForm> getEmployeesList() {
		
		List<EmployeeForm> listForm = new ArrayList<>();
		List<Employee> entityForm = employeeRepository.findAll();
		
		for (Employee employee : entityForm) {
			EmployeeForm form = new EmployeeForm();
			BeanUtils.copyProperties(employee, form);
			listForm.add(form);
		}
		
		return listForm;
	}

	@Override
	public EmployeeForm editEmployee(Integer id) {
		
		Optional<Employee> findById = employeeRepository.findById(id);
		if(findById.isPresent()) {
			Employee employee = findById.get();
			EmployeeForm form = new EmployeeForm();
			BeanUtils.copyProperties(employee, form);
			return form;
		}
		
		return null;
	}

	@Override
	public List<EmployeeForm> deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
		return getEmployeesList();
	}


}
