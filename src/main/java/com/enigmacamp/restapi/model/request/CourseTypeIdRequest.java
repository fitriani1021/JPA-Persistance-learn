package com.enigmacamp.restapi.model.request;

import javax.validation.constraints.NotBlank;

public class CourseTypeIdRequest {
    @NotBlank(message = "typeId is required")
    private String typeId;
    
    public String getTypeId() {
        return typeId;
    }
    
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    
    @Override
    public String toString() {
        return "CourseTypeIdRequest{" + "typeId='" + typeId + '\'' + '}';
    }
}
