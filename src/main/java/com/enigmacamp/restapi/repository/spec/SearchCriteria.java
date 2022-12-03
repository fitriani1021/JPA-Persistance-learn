package com.enigmacamp.restapi.repository.spec;

import com.enigmacamp.restapi.util.QueryOperator;

public class SearchCriteria {
    private String key;
    private QueryOperator operator;
    private Object value;
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public QueryOperator getOperator() {
        return operator;
    }
    
    public void setOperator(QueryOperator operator) {
        this.operator = operator;
    }
    
    public Object getValue() {
        return value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
}
