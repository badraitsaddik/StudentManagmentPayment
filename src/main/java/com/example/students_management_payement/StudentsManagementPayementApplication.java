package com.example.students_management_payement;

import com.example.students_management_payement.Entities.Payment;
import com.example.students_management_payement.Entities.PaymentStatus;
import com.example.students_management_payement.Entities.PaymentType;
import com.example.students_management_payement.Entities.Student;
import com.example.students_management_payement.repository.PaymentRepository;
import com.example.students_management_payement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StudentsManagementPayementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsManagementPayementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository , PaymentRepository paymentRepository){
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("badr").code("009988").programId("GLSID").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("Ayman").code("009977").programId("SDIA").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("amina").code("009966").programId("BDCC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("majda").code("009955").programId("GLSID").build());


            PaymentType[] paymentTypes = PaymentType.values();

            Random random = new Random();
            studentRepository.findAll().forEach(st -> {
                for (int i = 0; i < 10; i++) {
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(double)(Math.random()*20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);

                }

            });
        };
    }



}
