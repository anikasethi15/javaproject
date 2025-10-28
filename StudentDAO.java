package com.example.dao;

import com.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveStudent(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    public Student getStudent(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Student", Student.class).list();
    }

    public void updateStudent(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    public void deleteStudent(int id) {
        Student s = getStudent(id);
        if (s != null) sessionFactory.getCurrentSession().delete(s);
    }
}
