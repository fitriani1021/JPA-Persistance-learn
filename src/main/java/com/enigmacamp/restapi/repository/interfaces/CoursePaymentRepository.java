package com.enigmacamp.restapi.repository.interfaces;

import com.enigmacamp.restapi.model.request.CoursePaymentRequest;
import com.enigmacamp.restapi.model.response.CoursePaymentResponse;

public interface CoursePaymentRepository {
    CoursePaymentResponse callPaymentApi(CoursePaymentRequest coursePaymentRequest);
}
