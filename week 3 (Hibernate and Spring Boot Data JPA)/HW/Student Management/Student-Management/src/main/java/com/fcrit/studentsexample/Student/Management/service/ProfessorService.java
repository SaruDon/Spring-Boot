package com.fcrit.studentsexample.Student.Management.service;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import com.fcrit.studentsexample.Student.Management.entity.Student;
import com.fcrit.studentsexample.Student.Management.entity.Subject;
import com.fcrit.studentsexample.Student.Management.repository.ProfessorRepo;
import com.fcrit.studentsexample.Student.Management.repository.StudentRepository;
import com.fcrit.studentsexample.Student.Management.repository.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    final private ProfessorRepo professorRepo;
    final SubjectRepo subjectRepo;
    final StudentRepository studentRepository;

    public ProfessorService(ProfessorRepo professorRepo, SubjectRepo subjectRepo, StudentRepository studentRepository) {
        this.professorRepo = professorRepo;
        this.subjectRepo = subjectRepo;
        this.studentRepository = studentRepository;
    }

    public Professor getProfessorById(Long professorId) {
        return professorRepo.findById(professorId).orElse(null);
    }


    public Professor createProfessor(Professor professor) {
        return professorRepo.save(professor);
    }

    public Professor assignProfessorToSubject(Long professorId, Long subjectId) {
        Optional<Professor> professorE = professorRepo.findById(professorId);
        Optional<Subject> subjectE = subjectRepo.findById(subjectId);

        return professorE.flatMap(professor ->
                subjectE.map(subject -> {
                    subject.setProfessor(professor);
                    subjectRepo.save(subject);

                    professor.getSubjects().add(subject);
                    return professor;
                })).orElse(null);
    }

    public Professor assignProfessorToStudent(Long professorId, Long studentId) {
        Optional<Professor> professorE = professorRepo.findById(professorId);
        Optional<Student> studentE = studentRepository.findById(studentId);

        return professorE.flatMap(professor ->
                studentE.map(student -> {
                    student.getProfessorsOfStudents().add(professor);
                    studentRepository.save(student);

                    professor.getStudentsList().add(student);
                    return professor;
                })).orElse(null);
    }

    public List<Professor> getAllProfessor() {
        return professorRepo.findAll();
    }
}
