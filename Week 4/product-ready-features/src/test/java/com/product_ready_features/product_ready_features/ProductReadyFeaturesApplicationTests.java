package com.product_ready_features.product_ready_features;

import com.product_ready_features.product_ready_features.clients.EmployeeClient;
import com.product_ready_features.product_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployees(){
		List<EmployeeDTO> employeeDtoList = employeeClient.getAllEmployees();
		System.out.println(employeeDtoList);
	}

	@Test
	@Order(2)
	void getEmployeeById(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployeeTest(){
		EmployeeDTO employeeDTO1 = new EmployeeDTO(null,"sarvesh","savreshm@gmail.com"
				,"USER",21,5000.1,
				LocalDate.of(2020,12,1),true);
		EmployeeDTO savedEmployeeDto = employeeClient.createEmployee(employeeDTO1);
		System.out.println(savedEmployeeDto);
	}
}
