package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.exception.EntityExistException;
import com.enigmacamp.restapi.exception.NotFoundException;
import com.enigmacamp.restapi.model.CourseInfo;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.repository.CourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CourseInfoServiceImpl implements CourseInfoService{
    @Autowired
    private CourseInfoRepository courseInfoRepository;
    
    @Override
    public Page<CourseInfo> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page-1),size,sort);
        Page<CourseInfo> result = courseInfoRepository.findAll(pageable);
        return result;
    }
    
    @Override
    public CourseInfo create(CourseInfo courseInfo) throws Exception {
        try {
            CourseInfo newInfo = courseInfoRepository.save(courseInfo);
            return newInfo;
        } catch(DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }
    
    @Override
    public CourseInfo get(String id) throws Exception {
        Optional<CourseInfo> courseInfo = courseInfoRepository.findById(id);
        if(courseInfo.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return courseInfo.get();
    }
}
