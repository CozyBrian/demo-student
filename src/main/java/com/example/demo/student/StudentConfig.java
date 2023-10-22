package com.example.demo.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MAY;


@RequiredArgsConstructor
@Configuration
public class StudentConfig {
  private final StudentRepository repository;



  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository) {
    return args -> {
			Student cozy = new Student(1L, "Cozy", LocalDate.of(2002,Month.MAY, 14), "cozy@brian.com", 34);

            Student brian = new Student(2L, "Brian", LocalDate.of(2003,Month.JANUARY, 14), "brian@cozy.com",21);

      repository.saveAll(
        List.of(cozy, brian)
      );
    };
  }
}
