package com.example.dao;

import com.example.entity.Course;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveCourse(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }

    public Course getCourse(int id) {
        return sessionFactory.getCurrentSession().get(Course.class, id);
    }

    public List<Course> getAllCourses() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Course", Course.class).list();
    }
}
