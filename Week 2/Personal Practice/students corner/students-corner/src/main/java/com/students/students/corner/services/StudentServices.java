package com.students.students.corner.services;


import com.students.students.corner.dto.StudentsDto;
import com.students.students.corner.entity.StudentsEntity;
import com.students.students.corner.repository.StudentRepository;
import org.aspectj.util.Reflection;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServices {

    final private StudentRepository studentRepository;
    final private ModelMapper modelMapper;


    public StudentServices(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<StudentsDto> getStudentById(Long studentId) {
        return studentRepository.findById(studentId).map(departmentEntity -> modelMapper.map(departmentEntity, StudentsDto.class));    }

    public StudentsDto createStudent(StudentsDto studentsDto) {
        StudentsEntity studentToBeSaved = modelMapper.map(studentsDto,StudentsEntity.class);
        return modelMapper.map(studentRepository.save(studentToBeSaved),StudentsDto.class);

    }

    public StudentsDto updateStudentById(StudentsDto studentsDto, Long studentId) {
       StudentsEntity studentsEntityToChange = modelMapper.map(studentsDto,StudentsEntity.class);
       studentsEntityToChange.setStudentId(studentId);
       StudentsEntity studentsEntityToSave = studentRepository.save(studentsEntityToChange);
       return  modelMapper.map(studentsEntityToSave,StudentsDto.class);
    }

    public List<StudentsDto> getAllStudents(){
        List<StudentsEntity> listOfAllStudents = studentRepository.findAll();
        return listOfAllStudents
                .stream()
                .map(studentsEntity -> modelMapper.map(studentsEntity,StudentsDto.class))
                .collect(Collectors.toList());
    }


    public boolean exist(Long studentId){
        if(!studentRepository.existsById(studentId)) return false;
        return  true;
    }

    public StudentsDto updateFieldsById(Long studentId, Map<String, Object> updates) {
        // Check if student exists
        if (!studentRepository.existsById(studentId)) {
            return null;
        }

        // Retrieve the student entity
        StudentsEntity studentToUpdate = studentRepository.findById(studentId).orElse(null);
        if (studentToUpdate == null) {
            return null;
        }

        // Apply updates
        updates.forEach((fieldName, value) -> {
            Field field = ReflectionUtils.findField(StudentsEntity.class, fieldName);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, studentToUpdate, value);
            }
        });

        // Save and return the updated entity as DTO
        StudentsEntity updatedStudent = studentRepository.save(studentToUpdate);
        return modelMapper.map(updatedStudent, StudentsDto.class);
    }
}
