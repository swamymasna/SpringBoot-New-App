package com.swamy.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swamy.bindings.EmployeeForm;
import com.swamy.service.IEmployeeService;

@WebMvcTest
public class EmployeeRestControllerTest {

	@MockBean
	private IEmployeeService empService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void saveEmployeeTest() throws Exception {
		
		EmployeeForm form = new EmployeeForm();
		when(empService.saveEmployee(form)).thenReturn("saved");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(form);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee/save").contentType("application/json").content(jsonData);	
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		int actualValue = 201;
		assertEquals(status, actualValue);
	}
	
	@Test
	public void getEmployeesList() throws Exception {
		
		EmployeeForm form = new EmployeeForm();
		List<EmployeeForm> employeesList = empService.getEmployeesList();
		when(empService.getEmployeesList()).thenReturn(employeesList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/list");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		int actualValue = 200;
		assertEquals(status, actualValue);
	}
	
	@Test
	public void getEmployee() throws Exception {
		
		EmployeeForm form = new EmployeeForm();
		form.setEmpId(1); form.setEmpName("ABC"); form.setEmpSal(56000.00);
		
		when(empService.editEmployee(1)).thenReturn(form);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/1");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		int actualValue = 200;
		assertEquals(status, actualValue);
	}
	
	@Test
	public void deleteEmployee() throws Exception {
		
		EmployeeForm form = new EmployeeForm();
		form.setEmpId(1); form.setEmpName("ABC"); form.setEmpSal(56000.00);
		
		List<EmployeeForm> list = empService.deleteEmployee(1);
		when(empService.deleteEmployee(1)).thenReturn(list);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/1");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		int actualValue = 200;
		assertEquals(status, actualValue);
	}
}
