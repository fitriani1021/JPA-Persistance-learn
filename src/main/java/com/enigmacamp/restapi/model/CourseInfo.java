package com.enigmacamp.restapi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "course_info")
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "info_id")
    private String infoId;
    @Column
    private String duration;
    @Column
    private String level;
    
    public String getInfoId() {
        return infoId;
    }
    
    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        return "CourseInfo{" + "infoId='" + infoId + '\'' + ", duration='" + duration + '\'' + ", level='" + level + '\'' + '}';
    }
}
