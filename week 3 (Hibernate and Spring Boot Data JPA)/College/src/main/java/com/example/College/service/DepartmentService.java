package com.example.College.service;

import com.example.College.dto.DepartmentDto;
import com.example.College.dto.EmployeeDto;
import com.example.College.entity.Department;
import com.example.College.entity.Employee;
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
public class DepartmentService {

    private  final DepartmentRepository departmentRepository;
    private  final ModelMapper modelMapper;
    private  final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper, EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }


    public Optional<DepartmentDto> getDepartmentId(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department
                .map(department1 -> modelMapper.map(department1, DepartmentDto.class));
    }

    public DepartmentDto createDepartment(DepartmentDto department) {
        Department savedDepartment = departmentRepository.save(modelMapper.map(department,Department.class));
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public DepartmentDto updateDepartment(Long id, DepartmentDto department) {
        Department department1 = modelMapper.map(department,Department.class);
        department1.setId(id);
        Department updatedDepartment = departmentRepository.save(department1);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    public DepartmentDto partialUpdates(Long id, Map<String, Objects> updates) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()){
            throw  new Error("Department Not found with id "+id);
        }
        Department department1 = department.get();

        updates.forEach((field,value)->{
            Field fieldToUpdate = ReflectionUtils.findRequiredField(Department.class, field);
            if (fieldToUpdate != null) {
                fieldToUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToUpdate, department1, value);
            }
        });
        Department updatedDepartment = departmentRepository.save(department1);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments
                .stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());

    }


    public boolean existDepartment(Long id){
        return departmentRepository.existsById(id);
    }

    public Boolean deleteById(Long id) {
        if (!existDepartment(id)){
            return  false;
        }
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDto assignManagerToDepartment(Long departmentId, Long id) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        Employee employee = employeeRepository.findById(id).orElse(null);
        department.setManager(employee);
        employeeService.assignWorkerADepartment(id,departmentId);
        return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
    }


    public DepartmentDto getAssignedManagerOfDepartment(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(employee1 ->
                modelMapper.map(employee1.getManagedDepartment(), DepartmentDto.class)
                ).orElse(null);
    }
}
