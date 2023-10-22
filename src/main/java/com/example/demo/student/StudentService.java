package com.example.demo.student;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
		return studentRepository.findAll();
	}

  public Student getStudent(Long studentId) {
    Optional<Student> optionalStudent = studentRepository.findById(studentId);

    if (!optionalStudent.isPresent()) {
      throw new IllegalStateException("Student with id " + studentId + " does not exist");
    }

    return optionalStudent.get();
  }

  public void addNewStudent(Student student) {
    Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());

    if (optionalStudent.isPresent()) {
      throw new IllegalStateException("Email taken");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new IllegalStateException("Student with id " + studentId + " does not exist");
    }
    
    studentRepository.deleteById(studentId);
  }
  
  @Transactional
  public void updateStudent(Long studentId, Student student) {
    Student originalStudent = studentRepository.findById(studentId)
    .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));

    if (
      student.getName() != null && 
      student.getName().length() > 0 && 
      !student.getName().equals(originalStudent.getName())
    ) {
      originalStudent.setName(student.getName());
    }

    if (
      student.getEmail() != null && 
      student.getEmail().length() > 0 && 
      !student.getEmail().equals(originalStudent.getEmail())
    ) {
      Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());

      if (optionalStudent.isPresent()) {
        throw new IllegalStateException("Email taken");
      }

      originalStudent.setEmail(student.getEmail());
    }
    
  }

}
