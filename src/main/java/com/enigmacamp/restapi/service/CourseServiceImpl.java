package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.exception.EntityExistException;
import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.CourseRepository;
import com.enigmacamp.restapi.repository.CourseTypeRepository;
import com.enigmacamp.restapi.repository.spec.CourseSpec;
import com.enigmacamp.restapi.repository.spec.CourseTypeSpec;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import com.enigmacamp.restapi.util.QueryOperator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<Course> list() throws Exception {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }
    
    @Override
    public Page<Course> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1),size,sort);
        Page<Course> result = courseRepository.findAll(pageable);
        return result;
    }
    
    
    @Override
    public List<Course> findByTitleContains(String value) {
        List<Course> courses = courseRepository.findByTitleContains(value);
        if(courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " title is not found");
        }
        return courses;
    }
    
    @Override
    public List<Course> findByDescriptionContains(String value) {
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if(courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " description is not found");
        }
        return courses;
    }
    
    @Override
    public List<Course> getBy(String keyword, String value) {
        switch(keyword) {
            case "title":
                return findByTitleContains(value);
            case "description":
                return findByDescriptionContains(value);
            default:
                return courseRepository.findAll();
        }
    }
    
    @Override
    public Course create(Course course) {
        try {
            Optional<CourseType> courseType = courseTypeRepository.findById(course.getCourseType().getTypeId());
            if(courseType.isEmpty()){
                throw new NotFoundException("Course type is not found");
            }
            course.setCourseType(courseType.get());
            Course newCourse = courseRepository.save(course);
            return newCourse;
        } catch(DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }
    
    @Override
    public Course get(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return course.get();
    }
    
    @Override
    public void update(Course course, String id) {
        try {
            Course existingCourse = get(id);
            modelMapper.map(course, existingCourse);
            courseRepository.save(existingCourse);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }
    
    @Override
    public void delete(String id) {
        try {
            Course existingCourse = get(id);
            courseRepository.delete(existingCourse);
        } catch(NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
    
    @Override
    public List<Course> getBy(SearchCriteria searchCriteria) {
        Specification spec = new CourseSpec().findBy(searchCriteria);
        return courseRepository.findAll(spec);
    }
    
    @Override
    public List<Course> getByTypeId(String typeId) {
        Specification spec = new CourseSpec().courseTypeId(typeId);
        return courseRepository.findAll(spec);
    }
}
