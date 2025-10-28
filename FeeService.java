package com.example.service;

import com.example.dao.StudentDAO;
import com.example.entity.Payment;
import com.example.entity.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeeService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void makePayment(int studentId, double amount) {
        Student s = studentDAO.getStudent(studentId);
        if (s == null) throw new RuntimeException("Student not found!");

        if (s.getBalance() < amount)
            throw new RuntimeException("Insufficient balance for payment!");

        s.setBalance(s.getBalance() - amount);
        studentDAO.updateStudent(s);

        Payment payment = new Payment(studentId, amount);
        sessionFactory.getCurrentSession().save(payment);

        System.out.println("✅ Payment successful for student: " + s.getName());
    }

    @Transactional
    public void refundPayment(int studentId, double amount) {
        Student s = studentDAO.getStudent(studentId);
        if (s == null) throw new RuntimeException("Student not found!");

        s.setBalance(s.getBalance() + amount);
        studentDAO.updateStudent(s);

        Payment refund = new Payment(studentId, -amount);
        sessionFactory.getCurrentSession().save(refund);

        System.out.println("💰 Refund processed for student: " + s.getName());
    }
}
