package com.example.EcommerceSpringBootProject.controller;


import com.example.EcommerceSpringBootProject.constants.Message;
import com.example.EcommerceSpringBootProject.dto.CategoryDto;
import com.example.EcommerceSpringBootProject.entity.Category;
import com.example.EcommerceSpringBootProject.exception.NotFoundException;
import com.example.EcommerceSpringBootProject.response.DataResponse;
import com.example.EcommerceSpringBootProject.service.interfaces.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        try{
            List<CategoryDto> resultResponse = new ArrayList<>();
            iCategoryService.findAll().stream().forEach(item-> {
                CategoryDto categoryDto =  modelMapper.map(item, CategoryDto.class);
                resultResponse.add(categoryDto);
            });
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.success,resultResponse,null));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.failure,null,null));
        }

    }
    @PostMapping
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDto categoryDto){

       Category categoryRequest = modelMapper.map(categoryDto,Category.class);
       Category category = iCategoryService.save(categoryRequest);
       CategoryDto categoryResponse = modelMapper.map(category,CategoryDto.class);
       return ResponseEntity.ok().body(new DataResponse(HttpStatus.CREATED.value(),Message.success,categoryResponse,null));
    }


    @PutMapping("/{id}")
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        try{
            Category categoryRequest = modelMapper.map(categoryDto,Category.class);
            Boolean isUpdate = iCategoryService.update(categoryRequest,id);
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.success,isUpdate,null));
        }catch (NotFoundException ex){
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.failure,null,null));
        }
    }

    @DeleteMapping("/{id}")
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> update(@PathVariable Long id){
        try{
            Boolean isDelete = iCategoryService.delete(id);
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.success,isDelete,null));
        }catch (NotFoundException ex){
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.failure,null,null));
        }
    }
}
