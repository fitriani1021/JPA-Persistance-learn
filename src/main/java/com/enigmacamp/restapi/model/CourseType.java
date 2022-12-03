package com.enigmacamp.restapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course_type")
public class CourseType {
    @Id
    @Column(name="type_id")
    private String typeId;
    @Column(name="type_name", length = 50, unique = true)
    private String typeName;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courseType")
    @JsonManagedReference
    private Set<Course> courses = new HashSet<Course>();
    
    public Set<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    
    public String getTypeId() {
        return typeId;
    }
    
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    
    public String getTypeName() {
        return typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    @Override
    public String toString() {
        return "CourseType{" + "typeId='" + typeId + '\'' + ", typeName='" + typeName + '\'' + '}';
    }
}
