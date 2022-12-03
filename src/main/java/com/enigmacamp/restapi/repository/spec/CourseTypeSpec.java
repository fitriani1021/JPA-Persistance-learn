package com.enigmacamp.restapi.repository.spec;

import com.enigmacamp.restapi.model.CourseType;
import org.springframework.data.jpa.domain.Specification;

public class CourseTypeSpec {
    //    public Specification<CourseType>  typeNameLike(String name) {
//        return new Specification<CourseType>(){
//            @Override
//            public Predicate toPredicate(Root<CourseType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.like(root.get("typeName"),"%"+ name+"%");
//            }
//        };
//    }
    public Specification<CourseType> typeNameLike(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("typeName"), "%" + name + "%"));
    }
}
