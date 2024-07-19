package com.fcrit.onetoone.example.One.to.One.Many.service;

import com.fcrit.onetoone.example.One.to.One.Many.entities.DepartmentEntity;
import com.fcrit.onetoone.example.One.to.One.Many.entities.EmployeeEntity;
import com.fcrit.onetoone.example.One.to.One.Many.repository.DepartmentRepository;
import com.fcrit.onetoone.example.One.to.One.Many.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    final private DepartmentRepository departmentRepository;

    final private EmployeeRepository employeeRepository;


    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity getDepartmentById(Long departmentId) {
        return  departmentRepository.findById(departmentId).orElse(null);
    }

    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity) {
        return  departmentRepository.save(departmentEntity);
    }

    /*
1. departmentEntity.flatMap(department -> ...)
What it does: It checks if departmentEntity contains a value.
If departmentEntity is empty: It skips the inner code and moves to orElse(null).
If departmentEntity has a value: It runs the code inside the parentheses (the lambda function).

2. employeeEntity.map(employee -> { ... })
What it does: If departmentEntity is not empty, it checks if employeeEntity contains a value.
If employeeEntity is empty: It skips the inner code and returns an empty Optional.
If employeeEntity has a value: It runs the code inside the parentheses (the lambda function).

3. department.setManager(employee)
What it does: Inside the lambda function, it sets the employee as the manager of the department.

4. return departmentRepository.save(department)
What it does: It saves the updated department (with its new manager) to the database and returns the saved department.

5. orElse(null)
What it does: If either departmentEntity or employeeEntity was empty (meaning if either was not found), it returns null.
     */

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee ->{
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
    }


    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
//        return employeeEntity.map(EmployeeEntity::getManagedDepartment).orElse(null);

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();

        return departmentRepository.findByManager(employeeEntity);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee ->{
                   employee.setWorkerDepartment(department);
                   employeeRepository.save(employee);

                   department.getWorkers().add(employee);
                   return  department;
                })).orElse(null);
    }

    public DepartmentEntity assignFreeLancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee ->{
                    employee.getFreelanceDepartments().add(department);
                    employeeRepository.save(employee);
                    return department;
                })).orElse(null);
    }
}
