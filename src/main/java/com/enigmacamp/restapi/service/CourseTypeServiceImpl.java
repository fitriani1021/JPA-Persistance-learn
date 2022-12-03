package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.exception.EntityExistException;
import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.CourseRepository;
import com.enigmacamp.restapi.repository.CourseTypeRepository;
import com.enigmacamp.restapi.repository.spec.CourseTypeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourseTypeServiceImpl implements CourseTypeService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Override
    public Page<CourseType> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1),size,sort);
//        List<Course> courses = courseRepository.find
        Page<CourseType> result = courseTypeRepository.findAll(pageable);
        return result;
    }
    
    @Override
    public CourseType create(CourseType courseType) throws Exception {
        try {
//            List<Course> courses = courseRepository.
            CourseType newType = courseTypeRepository.save(courseType);
            return newType;
        } catch(DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }
    
    @Override
    public CourseType get(String id) throws Exception {
        Optional<CourseType> courseType = courseTypeRepository.findById(id);
        if(courseType.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return courseType.get();
    }
    
    @Override
    public List<Course> getCourseById(String id) throws Exception {
        return courseRepository.findAll().stream().filter(course -> course.getCourseType().getTypeId().equals(id)).collect(Collectors.toList());
    }
    
    @Override
    public List<CourseType> findCourseType(String name) {
        Specification spec = new CourseTypeSpec().typeNameLike(name);
        List<CourseType> result = courseTypeRepository.findAll(spec);
        return result;
    }
}
