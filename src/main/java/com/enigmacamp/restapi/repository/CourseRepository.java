package com.enigmacamp.restapi.repository;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    
    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
    List<Course> findByTitleContains(String title);
    
    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescriptionContains(String description);
    
    //    buat method untuk pagination (page, size, sortBy, direction)
//    @Query(value = "select * from tbl_course LIMIT ?1 OFFSET ?2 ORDER BY title desc", nativeQuery = true)
//    List<Course> findWithPagination(Integer size, Integer offset);
    List<Course> findAll(Specification specification);
}
