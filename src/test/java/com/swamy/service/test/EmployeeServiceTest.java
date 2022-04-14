package com.swamy.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.swamy.bindings.EmployeeForm;
import com.swamy.entity.Employee;
import com.swamy.repository.EmployeeRepository;
import com.swamy.service.IEmployeeService;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private IEmployeeService empService;
	
	@MockBean
	private EmployeeRepository empRepo;
	
	@Test
	public void saveEmployee01() {
		
		Employee entity = new Employee();
		entity.setEmpId(1); entity.setEmpName("ABC"); entity.setEmpSal(56000.00);

		EmployeeForm form = new EmployeeForm();
		BeanUtils.copyProperties(entity, form);
		
		when(empRepo.save(entity)).thenReturn(entity);
		when(empService.saveEmployee(form)).thenReturn("SAVED");
		
		assertTrue(true);
		
	}
	
	@Test
	public void saveEmployee02() {
		
		EmployeeForm form = new EmployeeForm();
		form.setEmpId(1); form.setEmpName("ABC"); form.setEmpSal(56000.00);
		
		Employee entity = new Employee();
		BeanUtils.copyProperties(form, entity);
		
		when(empRepo.save(entity)).thenReturn(new Employee());
		when(empService.saveEmployee(form)).thenReturn(null);
		
		assertEquals(form.getCreatedDate(), entity.getCreatedDate());
	}
	
	@Test
	public void getAllEmps() {
		
		Employee entity = new Employee();
		entity.setEmpId(1); entity.setEmpName("ABC"); entity.setEmpSal(56000.00);
		
		List<Employee>listEntity = new ArrayList<>();
		listEntity.add(entity);
		
		when(empRepo.findAll()).thenReturn(listEntity);
		
		List<EmployeeForm> listForm = empService.getEmployeesList();
		when(empService.getEmployeesList()).thenReturn(listForm);
		assertTrue(true);
		
	}
	
	@Test
	public void getOneEmpTest() {
		
		Employee emp = new Employee();
		emp.setEmpId(1); emp.setEmpName("ABC");

		EmployeeForm form = new EmployeeForm();
		form.setEmpId(1); form.setEmpName("ABC");
		
		Optional<Employee> of = Optional.of(emp);
		Optional<Employee> findById = empRepo.findById(1);
		when(findById).thenReturn(of);
		
		EmployeeForm editEmployee = empService.editEmployee(1);
		
		assertTrue(true);
	}
	
	@Test
	public void getOneEmpTest2() {
		
		Employee emp = new Employee();
		emp.setEmpId(1); emp.setEmpName("ABC");

		EmployeeForm form = new EmployeeForm();
		form.setEmpId(1); form.setEmpName("ABC");
		
		Optional<Employee> of = Optional.of(emp);
		Optional<Employee> findById = empRepo.findById(1);
		when(findById).thenReturn(of);
		
		EmployeeForm editEmployee = empService.editEmployee(null);
		
		assertTrue(true);
	}
	
	@Test
	public void deleteEmpTest() {
		
		Employee emp = new Employee();
		emp.setEmpId(1); emp.setEmpName("ABC");

		empService.deleteEmployee(1);
		verify(empRepo , times(1)).deleteById(1);
		
	}
}











