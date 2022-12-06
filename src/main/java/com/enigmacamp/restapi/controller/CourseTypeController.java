package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.CourseType;
import com.enigmacamp.restapi.model.response.PagingResponse;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.service.interfaces.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course-types")
@Validated
public class CourseTypeController {
    @Autowired
    private CourseTypeService courseTypeService;
    
    @GetMapping
    public ResponseEntity getAllTypes(
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "DESC") String direction, @RequestParam(defaultValue = "typeId") String sortBy) throws Exception{
        Page<CourseType> typeList = courseTypeService.list(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully get all types",typeList));
    }
    
    @PostMapping
    public ResponseEntity addType(@Valid @RequestBody CourseType courseType) throws Exception{
        CourseType newType = courseTypeService.create(courseType);
        newType.setCourses(courseType.getCourses());
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Created Type",newType));
    }
    
    @GetMapping("/name={name}")
    public ResponseEntity getCourseTypeByName(@PathVariable("name") String name) throws Exception{
        List<CourseType> courseType = courseTypeService.findCourseType(name);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Course By Name", courseType));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getCourseTypeById(@PathVariable("id") String id) throws Exception{
        CourseType courseType = courseTypeService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Course By ID", courseType));
    }
    
}
