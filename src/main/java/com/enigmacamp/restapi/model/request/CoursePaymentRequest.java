package com.enigmacamp.restapi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

public class CoursePaymentRequest {
    @Setter     @Getter
    private Integer transactionId;
    @Setter     @Getter
    private Integer customerId;
    @Setter     @Getter
    private String transactionType;
//    harus disamain dengan apinya
}
