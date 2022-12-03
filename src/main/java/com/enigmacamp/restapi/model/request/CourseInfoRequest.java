package com.enigmacamp.restapi.model.request;

import javax.persistence.Column;

public class CourseInfoRequest {
    @Column(nullable = false,length = 50)
    private String duration;
    @Column(nullable = false,length = 10)
    private String level;
    
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
        return "CourseInfoRequest{" + "duration='" + duration + '\'' + ", level='" + level + '\'' + '}';
    }
}
