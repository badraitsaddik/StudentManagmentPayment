package com.example.students_management_payement.repository;

import com.example.students_management_payement.Entities.Payment;
import com.example.students_management_payement.Entities.PaymentStatus;
import com.example.students_management_payement.Entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
