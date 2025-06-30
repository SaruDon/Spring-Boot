package com.example.Office.services;

import com.example.Office.dto.ProfessorDto;
import com.example.Office.entity.Professor;
import com.example.Office.entity.Student;
import com.example.Office.repository.ProfessorRepository;
import com.example.Office.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {


    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;
    private  final StudentRepository studentRepository;

    public ProfessorService(ProfessorRepository professorRepository, ModelMapper modelMapper, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }


    public List<ProfessorDto> getAllProfessor() {
        return  professorRepository.findAll().stream().map(professor -> modelMapper.map(professor,ProfessorDto.class)).collect(Collectors.toList());
    }

    public Optional<ProfessorDto> getProfessorById(Long id) {
        return professorRepository.findById(id).map(professor -> modelMapper.map(professor,ProfessorDto.class));
    }

    public ProfessorDto createProfessor(ProfessorDto professorDto) {
        return modelMapper.map(professorRepository.save(modelMapper.map(professorDto,Professor.class)),ProfessorDto.class);
    }

    public ProfessorDto updateProfessor(ProfessorDto professorDto, Long id) {
        Professor professor = modelMapper.map(professorDto,Professor.class);
        professor.setProfessor_id(id);
        return modelMapper.map(professorRepository.save(professor), ProfessorDto.class);
    }

    public ProfessorDto partialUpdate(Map<String, Object> updates, Long id) {
        Professor professor =  professorRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));

        BeanWrapper beanWrapper = new BeanWrapperImpl(professor);
        updates.forEach((property,value)->{
            if (beanWrapper.isWritableProperty(property)){
                beanWrapper.setPropertyValue(property,value);
            }else{
                throw new IllegalArgumentException("Property '" + property + "' not found on Student");
            }
        });
        return  modelMapper.map(professor,ProfessorDto.class);
    }


    public boolean professorExist(Long id){
        return professorRepository.existsById(id);
    }

    public Boolean deleteProfessorById(Long id) {
        if (!professorExist(id)){
            throw  new EntityNotFoundException("Professor Does not exists");
        }
        professorRepository.deleteById(id);
        return  true;
    }

    public ProfessorDto addStudent(Long id, Long studentId) {
        Professor professor = professorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Prof not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Student not found"));

        if (!professor.getStudents().contains(student)){
            professor.getStudents().add(student);
        }
        if (!student.getProfessors().contains(professor)){
            student.getProfessors().add(professor);
        }

        return  modelMapper.map(professorRepository.save(professor),ProfessorDto.class);
    }
}
