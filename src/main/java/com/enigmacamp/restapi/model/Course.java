package com.enigmacamp.restapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_course")
public class Course {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Column(name = "link", nullable = false, length =200)
    private String link;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false)
    @JsonBackReference
    private CourseType courseType;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseInfo courseInfo;
    
    public CourseInfo getCourseInfo() {
        return courseInfo;
    }
    
    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }
    
    public CourseType getCourseType() {
        return courseType;
    }
    
    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
    
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
