package com.fcrit.week2HomeWorkexample.Department.service;

import com.fcrit.week2HomeWorkexample.Department.dto.DepartmentDto;
import com.fcrit.week2HomeWorkexample.Department.entity.DepartmentEntity;
import com.fcrit.week2HomeWorkexample.Department.repository.DepartmentRepository;
import org.aspectj.util.Reflection;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    final private DepartmentRepository departmentRepository;
    final private ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDto> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDto.class));
    }

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntityToSave = modelMapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntityToSave);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public List<DepartmentDto> getListOfDepartment() {
        List<DepartmentEntity> departmentEntitiesList = departmentRepository.findAll();
        return departmentEntitiesList
                .stream()
                .map(departmentEntity ->  modelMapper.map(departmentEntity,DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public boolean departmentExist(Long departmentId){
        return departmentRepository.existsById(departmentId);
    }

    public boolean deleteDepartment(Long departmentId) {
        if (!departmentExist(departmentId)) return false;
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDto updateDepartmentByID(DepartmentDto departmentDto, Long departmentId) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto,DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    public DepartmentDto updatePartialDepartmentById(Long departmentId, Map<String, Object> updates) {
        if (!departmentExist(departmentId)) return null;
        DepartmentEntity departmentToUpdate = departmentRepository.findById(departmentId).get();
        updates.forEach((field,value)->{
            Field fieldsToUpdate = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            fieldsToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldsToUpdate,departmentToUpdate,value);
        });
        return modelMapper.map(departmentRepository.save(departmentToUpdate), DepartmentDto.class);
    }
}
