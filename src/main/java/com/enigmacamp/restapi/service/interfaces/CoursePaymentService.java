package com.enigmacamp.restapi.service.interfaces;

import com.enigmacamp.restapi.model.request.CoursePaymentRequest;
import com.enigmacamp.restapi.model.response.CoursePaymentResponse;

public interface CoursePaymentService {
    CoursePaymentResponse pay(CoursePaymentRequest coursePaymentRequest);
}
