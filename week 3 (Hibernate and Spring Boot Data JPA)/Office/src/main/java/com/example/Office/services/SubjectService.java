package com.example.Office.services;

import com.example.Office.dto.SubjectDto;
import com.example.Office.entity.Professor;
import com.example.Office.entity.Subject;
import com.example.Office.exception.ReSourceNotFoundException;
import com.example.Office.repository.ProfessorRepository;
import com.example.Office.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    private  final ProfessorRepository professorRepository;

    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper, ProfessorRepository professorRepository) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
        this.professorRepository = professorRepository;
    }


    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream().map(subject -> modelMapper.map(subject,SubjectDto.class)).collect(Collectors.toList());
    }

    public Optional<SubjectDto> getSubjectById(Long id) {
        return subjectRepository.findById(id).map(subject -> modelMapper.map(subject,SubjectDto.class));
    }

    public SubjectDto createSubject(SubjectDto subjectDto) {
        return modelMapper.map(subjectRepository.save(modelMapper.map(subjectDto,Subject.class)),SubjectDto.class);
    }

    public boolean isSubjectExist(Long id){
        return subjectRepository.existsById(id);
    }

    public Boolean deleteById(Long id) {
        if (!isSubjectExist(id)){
            throw new EntityNotFoundException("Subject Not found");
        }
        subjectRepository.deleteById(id);
        return true;
    }

    public SubjectDto assignProfessorASubject(Long subjectId, Long professorId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(()-> new ReSourceNotFoundException("SubjectNot Found"));
        Professor professor = professorRepository.findById(professorId).orElseThrow(()-> new ReSourceNotFoundException("Professor Does not exist"));

        if (subject.getProfessor() == professor){
            throw  new RuntimeException("Professor Already Assigned");
        }
        if (professor.getSubjects().contains(subject)){
            throw  new RuntimeException("Subject Already Assigned To This Professor");
        }
        subject.setProfessor(professor);
        professor.getSubjects().add(subject);
        subjectRepository.save(subject);
        return modelMapper.map(subject,SubjectDto.class);
    }
}
