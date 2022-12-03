package com.enigmacamp.restapi.repository.spec;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class CourseSpec {
    public Specification<Course> titleLike(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }
    
    public Specification<Course> descriptionLike(String desc) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + desc + "%");
    }
    
    public Specification<Course> titleOrDescription(String title) {
        return titleLike(title).or(descriptionLike(title));
    }
    
    public Specification<Course> courseTypeId(String courseTypeId) {
        return (root, query, criteriaBuilder) -> {
            Join<Course, CourseType> courseTypeJoin = root.join("courseType");
            return criteriaBuilder.equal(courseTypeJoin.get("typeId"), courseTypeId);
        };
    }
    
    public Specification<Course> findBy(SearchCriteria searchCriteria) {
        switch(searchCriteria.getOperator()) {
            case LIKE:
                return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            case EQUALS:
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            default:
                throw new RuntimeException("Operator not supported");
        }
    }
}
