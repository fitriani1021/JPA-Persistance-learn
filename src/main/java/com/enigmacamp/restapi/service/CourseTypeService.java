package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseTypeService {
    Page<CourseType> list(Integer page, Integer size, String direction, String sortBy) throws Exception;
    CourseType create(CourseType courseType) throws Exception;
    CourseType get(String id) throws Exception;
    List<Course> getCourseById(String id) throws Exception;
    List<CourseType> findCourseType(String name);
}
