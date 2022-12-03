package com.enigmacamp.restapi.service;

import com.enigmacamp.restapi.model.CourseInfo;
import org.springframework.data.domain.Page;

public interface CourseInfoService {
    Page<CourseInfo> list(Integer page, Integer size, String direction, String sortBy) throws Exception;
    CourseInfo create(CourseInfo courseInfo) throws Exception;
    CourseInfo get(String id) throws Exception;
}
