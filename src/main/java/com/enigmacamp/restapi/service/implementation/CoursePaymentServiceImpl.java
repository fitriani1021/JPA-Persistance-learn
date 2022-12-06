package com.enigmacamp.restapi.service.implementation;

import com.enigmacamp.restapi.model.request.CoursePaymentRequest;
import com.enigmacamp.restapi.model.response.CoursePaymentResponse;
import com.enigmacamp.restapi.repository.interfaces.CoursePaymentRepository;
import com.enigmacamp.restapi.service.interfaces.CoursePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePaymentServiceImpl implements CoursePaymentService {
    private CoursePaymentRepository coursePaymentRepository;
    
    public CoursePaymentServiceImpl(@Autowired CoursePaymentRepository coursePaymentRepository) {
        this.coursePaymentRepository = coursePaymentRepository;
    }
    
    @Override
    public CoursePaymentResponse pay(CoursePaymentRequest coursePaymentRequest) {
        return coursePaymentRepository.callPaymentApi(coursePaymentRequest);
    }
}
