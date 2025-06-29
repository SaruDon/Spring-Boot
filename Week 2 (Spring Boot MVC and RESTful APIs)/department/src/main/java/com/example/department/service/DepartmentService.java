package com.example.department.service;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.DepartmentEntity;
import com.example.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentService {

    private final ModelMapper modelMapper;

    private final DepartmentRepository departmentRepository;

    public DepartmentService(ModelMapper modelMapper, DepartmentRepository departmentRepository) {
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }


    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentEntity> departments = departmentRepository.findAll();
        return departments
                .stream()
                .map(dept -> modelMapper.map(dept, DepartmentDto.class))
                .collect(Collectors.toList());
    }


    private boolean departmentExist(Long departmentId) {
        return departmentRepository.existsById(departmentId);
    }

    public Optional<DepartmentDto> getDepartmentById(Long departmentId) {
        if (!departmentExist(departmentId)) {
            return null;
        }
        return departmentRepository.findById(departmentId).map(dept -> modelMapper.map(dept, DepartmentDto.class));
    }

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        DepartmentEntity department = modelMapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
        return savedDepartmentDto;
    }

    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        if (!departmentExist(id)) {
            return null;
        }
        DepartmentEntity toBeUpdatedDepartment = modelMapper.map(departmentDto, DepartmentEntity.class);
        toBeUpdatedDepartment.setId(id);
        DepartmentEntity updatedDepartmentDto = departmentRepository.save(toBeUpdatedDepartment);
        return modelMapper.map(updatedDepartmentDto, DepartmentDto.class);
    }

    public DepartmentDto partialUpdate(Map<String, Object> updates, Long id) {
        Optional<DepartmentEntity> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isEmpty()) {
            return null;
        }

        DepartmentEntity department = optionalDepartment.get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
            if (fieldToUpdate != null) {
                fieldToUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToUpdate, department, value);
            }
        });

        DepartmentEntity updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

}