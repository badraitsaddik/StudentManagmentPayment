package com.example.students_management_payement.web;

import com.example.students_management_payement.Entities.Payment;
import com.example.students_management_payement.Entities.PaymentStatus;
import com.example.students_management_payement.Entities.PaymentType;
import com.example.students_management_payement.Entities.Student;
import com.example.students_management_payement.repository.PaymentRepository;
import com.example.students_management_payement.repository.StudentRepository;
import com.example.students_management_payement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class PaymentController {
    StudentRepository studentRepository;
    PaymentRepository paymentRepository;
    PaymentService paymentService;

    public PaymentController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/payments")

    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")

    public List<Payment> paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/byStatus")

    public List<Payment> PaymentByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/byType")

    public List<Payment> PaymentByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payment/{id}")

    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping(path = "/students")

    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}")

    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentsByProgramId")

    public List<Student> getStudentsByProgramId(@RequestParam String programId){

        return studentRepository.findByProgramId(programId);
    }


    @PutMapping(path ="/payment/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status ,@PathVariable Long id){

        return paymentService.updatePaymentStatus(status,id);
    }

    @PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file , LocalDate date ,double amount ,PaymentType type ,
                               String studentCode) throws IOException {

        return paymentService.savePayment(file,date,amount,type,studentCode);


    }

    @GetMapping(path = "/paymentFile/{paymentId}" ,produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getFilePayment(@PathVariable Long paymentId) throws IOException {

        return this.paymentService.getFilePayment(paymentId);
    }

}
