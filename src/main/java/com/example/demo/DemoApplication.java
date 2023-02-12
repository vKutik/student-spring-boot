package com.example.demo;

import com.example.demo.repository.StudentRepository;
import com.example.demo.model.Address;
import com.example.demo.model.Gender;
import com.example.demo.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address(
					"Canada",
					"Montreal",
					"H2Z2"

			);
			String email = "vova@gmail.com";
			Student student = new Student(
					"Vova",
					"Kutovyi",
					email,
					Gender.MALE,
					address,
					List.of("Computer Science", "Math"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);

			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));
			List<Student> students = mongoTemplate.find(query, Student.class);

			if (students.size() > 1) {
				throw new IllegalStateException("found many students with email " + email);
			}
			if (students.isEmpty()) {
				System.out.println("Inserting student " + student);
				studentRepository.insert(student);
			} else {
				System.out.println(student + " already exists");
			}
		};
	}
}
