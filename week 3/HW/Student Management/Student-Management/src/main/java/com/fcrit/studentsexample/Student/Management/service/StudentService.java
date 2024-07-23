package com.fcrit.studentsexample.Student.Management.service;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import com.fcrit.studentsexample.Student.Management.entity.Student;
import com.fcrit.studentsexample.Student.Management.entity.Subject;
import com.fcrit.studentsexample.Student.Management.repository.StudentRepository;
import com.fcrit.studentsexample.Student.Management.repository.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    final private StudentRepository studentRepository;
    final private SubjectRepo subjectRepo;

    public StudentService(StudentRepository studentRepository, SubjectRepo subjectRepo) {
        this.studentRepository = studentRepository;
        this.subjectRepo = subjectRepo;
    }

    public Student getReferenceById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student assignStudentToSubject(Long studentId, Long subjectId) {
        Optional<Student> studentE = studentRepository.findById(studentId);
        Optional<Subject> subjectE = subjectRepo.findById(subjectId);

        return studentE.flatMap(student ->
                subjectE.map(subject ->
                        {
                            subject.getStudentList().add(student);
                            subjectRepo.save(subject);

                            student.getSubjects().add(subject);
                            return student;

                        }
                )).orElse(null);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

//    public Student assignSubjectToStudent(Long studentId, Long subjectId) {
//        Optional<Student> studentE = studentRepository.findById(studentId);
//        Optional<Subject> subjectE = subjectRepo.findById(subjectId);
//
//        return studentE.flatMap(student ->
//                subjectE.map(subject -> {
//                    subjectE.getStudents
//                }))
//
//    }
}
