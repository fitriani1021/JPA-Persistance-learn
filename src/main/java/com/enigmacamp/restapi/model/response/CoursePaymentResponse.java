package com.enigmacamp.restapi.model.response;

import lombok.Getter;
import lombok.Setter;

public class CoursePaymentResponse {
    @Getter
    @Setter
    private String transactionId;
    @Getter     @Setter
    private boolean status;
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
}
