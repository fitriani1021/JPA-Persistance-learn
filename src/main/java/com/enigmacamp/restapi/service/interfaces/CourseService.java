package com.enigmacamp.restapi.service.interfaces;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CourseService {
    List<Course> list() throws Exception;
    Page<Course> list(Integer page, Integer size, String direction, String sortBy) throws Exception;
    List<Course> findByTitleContains(String value);
    List<Course> findByDescriptionContains(String value);
    List<Course> getBy(String keyword, String value) throws Exception;
    Course create(Course course) throws Exception;
    Course get(String id) throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
    List<Course> getBy(SearchCriteria searchCriteria);
    List<Course> getByTypeId(String typeId);
}
