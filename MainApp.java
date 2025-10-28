package com.example;

import com.example.config.AppConfig;
import com.example.dao.CourseDAO;
import com.example.dao.StudentDAO;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.service.FeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO studentDAO = context.getBean(StudentDAO.class);
        CourseDAO courseDAO = context.getBean(CourseDAO.class);
        FeeService feeService = context.getBean(FeeService.class);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. View Students");
            System.out.println("4. Make Payment");
            System.out.println("5. Refund Payment");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Course name: ");
                    String cname = sc.next();
                    System.out.print("Duration: ");
                    String dur = sc.next();
                    courseDAO.saveCourse(new Course(cname, dur));
                    System.out.println("✅ Course added!");
                    break;

                case 2:
                    System.out.print("Student name: ");
                    String sname = sc.next();
                    System.out.print("Course ID: ");
                    int cid = sc.nextInt();
                    Course c = courseDAO.getCourse(cid);
                    if (c == null) {
                        System.out.println("⚠️ Invalid course!");
                        break;
                    }
                    System.out.print("Initial balance: ");
                    double bal = sc.nextDouble();
                    studentDAO.saveStudent(new Student(sname, c, bal));
                    System.out.println("✅ Student added!");
                    break;

                case 3:
                    studentDAO.getAllStudents().forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    try {
                        feeService.makePayment(sid, amt);
                    } catch (Exception e) {
                        System.out.println("❌ Transaction failed: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Student ID: ");
                    sid = sc.nextInt();
                    System.out.print("Refund Amount: ");
                    amt = sc.nextDouble();
                    try {
                        feeService.refundPayment(sid, amt);
                    } catch (Exception e) {
                        System.out.println("❌ Refund failed: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("👋 Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        context.close();
    }
}
