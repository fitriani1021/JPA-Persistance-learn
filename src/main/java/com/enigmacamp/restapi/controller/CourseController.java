package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.Course;
import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.model.request.CourseRequest;
import com.enigmacamp.restapi.model.response.PagingResponse;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.repository.spec.SearchCriteria;
import com.enigmacamp.restapi.service.CourseService;
import com.enigmacamp.restapi.util.QueryOperator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseService courseService;
//    @Autowired
//    private CourseType courseType;
    
    @GetMapping
    public ResponseEntity getAllCourse(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "DESC") String direction, @RequestParam(defaultValue = "courseId") String sortBy) throws Exception {
        Page<Course> courseList = courseService.list(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully Get All Course", courseList));
    }
    
    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest course) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Course newCourse = modelMapper.map(course, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Succesfully Created Course", result));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") String id) throws Exception {
        Course course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Course By ID", course));
    }
    
    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getByParam(@RequestParam @NotBlank(message = "keyword is required") String keyword, @RequestParam @NotBlank(message = "value is required") String value) throws Exception {
        List<Course> courses = courseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succesfully Get Course By " + keyword, courses));
        
    }
    
    @GetMapping(params = {"key", "operator", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String operator, @RequestParam String value) throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey(key);
        searchCriteria.setOperator(QueryOperator.valueOf(operator));
        searchCriteria.setValue(value);
        List<Course> courses = courseService.getBy(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succesfully Get Course By criteria "+searchCriteria.getKey(),courses));
    }
    
    @GetMapping("/type={typeId}")
    public ResponseEntity getCourseByTypeId(@PathVariable("typeId") String typeId) throws Exception{
        List<Course> courses = courseService.getByTypeId(typeId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Course By Type", courses));
    }
    
    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable("id") String id) throws Exception {
        courseService.delete(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateCourseById(@Valid @RequestBody CourseRequest course, @PathVariable("id") String id) throws Exception {
        Course existingCourse = modelMapper.map(course, Course.class);
        existingCourse.setCourseId(id);
        courseService.update(existingCourse, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update course", course));
    }
}
