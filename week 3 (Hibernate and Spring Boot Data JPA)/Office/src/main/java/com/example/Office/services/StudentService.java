package com.example.Office.services;

import com.example.Office.config.ModelMapperConfig;
import com.example.Office.dto.StudentDto;
import com.example.Office.entity.Student;
import com.example.Office.entity.Subject;
import com.example.Office.repository.StudentRepository;
import com.example.Office.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;


    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(student -> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
    }

    public StudentDto createStudent(StudentDto studentDto) {
        return modelMapper.map(studentRepository.save(modelMapper.map(studentDto, Student.class)),StudentDto.class);
    }

    public Optional<StudentDto> getStudentsById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student
                .map(student1 -> modelMapper.map(student1,StudentDto.class));
    }

    public boolean isStudentExists(Long id){
        return studentRepository.existsById(id);
    }

    public Boolean deleteById(Long id) {
        if (!isStudentExists(id)){
            throw  new Error("Student do not exists");
        }
        studentRepository.deleteById(id);
        return true;
    }

    public StudentDto updateById(StudentDto studentDto,Long id) {
        studentDto.setStudent_id(id);
        return modelMapper.map(studentRepository.save(modelMapper.map(studentDto,Student.class)),StudentDto.class);
    }


    public StudentDto partialUpdatesById(Long id, Map<String,Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Student not found"));
        BeanWrapper beanWrapper= new BeanWrapperImpl(student);
        updates.forEach((propertyName,propertyValue)->{
            if (beanWrapper.isWritableProperty(propertyName)){
                beanWrapper.setPropertyValue(propertyName,propertyValue);
            }else{
                throw new IllegalArgumentException("Property '" + propertyName + "' not found on Student");
            }
        });
        return modelMapper.map(studentRepository.save(student),StudentDto.class);
    }

//    public StudentDto addSubjectToStudent(Long id, Long subjectId) {
//        Student student = studentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Student no found"));
//        Subject subject = subjectRepository.findById(subjectId).orElseThrow(()-> new EntityNotFoundException("Subject not found"));
//
//
//        student.getSubjects().add(subject);
//        studentRepository.save(student);
//        return modelMapper.map(student,StudentDto.class);
//    }
        public StudentDto addSubjectToStudent(Long id, Long subjectId) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found"));
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

            // Avoid duplicates
            if (!student.getSubjects().contains(subject)) {
                student.getSubjects().add(subject);
            }
            if (!subject.getStudents().contains(student)) {
                subject.getStudents().add(student);
            }

            studentRepository.save(student);
            return modelMapper.map(student, StudentDto.class);
        }

}
