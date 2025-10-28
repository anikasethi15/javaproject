package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;
    private String duration;

    public Course() {}

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getDuration() { return duration; }

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setDuration(String duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "Course [id=" + courseId + ", name=" + courseName + ", duration=" + duration + "]";
    }
}
