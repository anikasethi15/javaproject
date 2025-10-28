package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private double balance;

    public Student() {}

    public Student(String name, Course course, double balance) {
        this.name = name;
        this.course = course;
        this.balance = balance;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public Course getCourse() { return course; }
    public double getBalance() { return balance; }

    public void setName(String name) { this.name = name; }
    public void setCourse(Course course) { this.course = course; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Student [id=" + studentId + ", name=" + name +
               ", course=" + (course != null ? course.getCourseName() : "None") +
               ", balance=" + balance + "]";
    }
}
