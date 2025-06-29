package com.example.College.service;

import com.example.College.dto.EmployeeDto;
import com.example.College.entity.Department;
import com.example.College.entity.Employee;
import com.example.College.entity.enums.EmpType;
import com.example.College.repository.DepartmentRepository;
import com.example.College.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private  final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }

    public Optional<EmployeeDto> getEmployee(Long id) {
        return  employeeRepository.findById(id).map(employee -> modelMapper.map(employee, EmployeeDto.class));
    }

    public EmployeeDto createEmployee(EmployeeDto inputEmployee) {
        Employee employeeTobeSaved = modelMapper.map(inputEmployee,Employee.class);
        Employee savedEmployee = employeeRepository.save(employeeTobeSaved);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }


    public EmployeeDto updateById(Long id, EmployeeDto employee) {
        Employee employee1 = modelMapper.map(employee,Employee.class);
        employee1.setId(id);
        Employee updatedEmployee = employeeRepository.save(employee1);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    public EmployeeDto partialUpdate(Long id, Map<String, Objects> updates) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()){
                throw  new Error("Employee Not found with id "+id);
        }

        Employee employee = employeeOpt.get();
        updates.forEach((field,value)->{
            Field fieldToUpdate = ReflectionUtils.findRequiredField(Employee.class, field);
            if (fieldToUpdate != null) {
                fieldToUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToUpdate, employee, value);
            }
        });
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    public boolean existEmployee(Long id){
        return employeeRepository.existsById(id);
    }


    public boolean deleteById(Long id) {
        if (!existEmployee(id)){
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees
                .stream()
                .map(employee -> modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto assignWorkerADepartment(Long empId, Long departmentId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        Optional<Department> department = departmentRepository.findById(departmentId);

        return  employee.flatMap(employee1 -> department.map(department1 -> {
            employee1.setWorkerDepartment(department1);
            return modelMapper.map(employeeRepository.save(employee1), EmployeeDto.class);
        })).orElse(null);
    }

    public EmployeeDto assignFreelancerADepartment(Long empId, Long departmentId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        if (employee.getType() != EmpType.FREELANCER) {
            throw new IllegalArgumentException("Not a freelancer");
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        // Avoid duplicate mapping
        if (!employee.getFreelances().contains(department)) {
            employee.getFreelances().add(department);
            employeeRepository.save(employee); // Save owning side
        }

        return modelMapper.map(employee, EmployeeDto.class);
    }


}
