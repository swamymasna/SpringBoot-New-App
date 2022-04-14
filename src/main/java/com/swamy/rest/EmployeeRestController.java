package com.swamy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.bindings.EmployeeForm;
import com.swamy.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService employeeService;
	
	@PostMapping("/save")
	public ResponseEntity<String>saveEmployee(@RequestBody EmployeeForm form){
		String body = employeeService.saveEmployee(form);
		return new ResponseEntity<String>(body, HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<EmployeeForm>>getAllEmployees() {
		List<EmployeeForm> list = employeeService.getEmployeesList();
		return new ResponseEntity<List<EmployeeForm>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeForm>editEmployee(@PathVariable Integer id) {
		EmployeeForm employee = employeeService.editEmployee(id);
		return new ResponseEntity<EmployeeForm>(employee, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<EmployeeForm>>deleteEmployee(@PathVariable Integer id) {
		List<EmployeeForm> list = employeeService.deleteEmployee(id);
		return new ResponseEntity<List<EmployeeForm>>(list, HttpStatus.OK);
	}
}














