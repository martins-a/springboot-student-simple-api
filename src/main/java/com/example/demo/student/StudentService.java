package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
//        return List.of(new Student(
//                1L,
//                "Maria",
//                21,
//                LocalDate.of(2000, Month.JANUARY, 5),
//                "mariam.jamal@gmail.com")
//        );

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if ( studentByEmail.isPresent() ) {
            throw new IllegalStateException(("email taken"));
        }

        studentRepository.save(student);
    }
}
