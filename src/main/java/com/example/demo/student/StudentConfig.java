package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
  
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository) {
    return args -> {
			Student cozy = new Student(
        "Cozy", 
        21, 
        LocalDate.of(2002, Month.MAY, 14), 
        "cozy@brian.com"
      );

      Student brian = new Student(
        "Brian", 
        19, 
        LocalDate.of(2003, Month.JANUARY, 14), 
        "brian@cozy.com"
      );

      repository.saveAll(
        List.of(cozy, brian)
      );
    };
  }
}
