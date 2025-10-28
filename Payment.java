package com.example.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private int studentId;
    private double amount;
    private Date date;

    public Payment() {}

    public Payment(int studentId, double amount) {
        this.studentId = studentId;
        this.amount = amount;
        this.date = new Date();
    }
}
