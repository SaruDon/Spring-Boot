package com.example.production_feature;

import com.example.production_feature.clients.EmployeeClient;
import com.example.production_feature.dto.EmployeeDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductionFeatureApplicationTests {

//	@Autowired
//	private EmployeeClient employeeClient;
//
//	@Test
//	@Order(3)
//	void getAllEmployees() {
//		List<EmployeeDto> employeeDtoList = employeeClient.getAllEmployees();
//		System.out.println(employeeDtoList);
//	}
//
//
//	@Test
//	@Order(1)
//	void createNewEmployeeTest(){
//		EmployeeDto employeeDto = new EmployeeDto(
//				null,
//				"katu",
//				LocalDate.parse("2024-01-01"),
//				100000.11f, // 👈 ensure Float type
//				"ADMIN",
//				true
//		);
//		EmployeeDto savedEmployeeDto  = employeeClient.createNewEmployee(employeeDto);
//		System.out.println(savedEmployeeDto);
//	}
//
//	@Test
//	@Order(2)
//	void getEmployeeById(){
//		System.out.println(employeeClient.getEmployeeById(1L));
//	}


}
