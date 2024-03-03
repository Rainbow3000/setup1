package com.example.EcommerceSpringBootProject.controller;
import com.example.EcommerceSpringBootProject.constants.Message;
import com.example.EcommerceSpringBootProject.dto.ProductDto;
import com.example.EcommerceSpringBootProject.entity.Product;
import com.example.EcommerceSpringBootProject.exception.NotFoundException;
import com.example.EcommerceSpringBootProject.response.DataResponse;
import com.example.EcommerceSpringBootProject.service.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?>findAll(){
        List<ProductDto> resultResponse = new ArrayList<>();
        iProductService.findAll().stream().forEach(item-> {
            ProductDto productDto =  modelMapper.map(item, ProductDto.class);
            resultResponse.add(productDto);
        });
        return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(), Message.success, resultResponse,null));
    }
    @PostMapping
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> create(@RequestBody @Valid ProductDto productDto){
            try{
                Product productRequest = modelMapper.map(productDto, Product.class);
                Product product = iProductService.save(productRequest, productDto.getCategoryId());
                ProductDto productResponse = modelMapper.map(product, ProductDto.class);
                return ResponseEntity.ok().body(new DataResponse(HttpStatus.CREATED.value(),Message.success, productResponse,null));
            }catch (NotFoundException ex){
                return ResponseEntity.ok().body(new DataResponse(HttpStatus.NOT_FOUND.value(),Message.failure, null, ex.getMessage()));
            }


    }


    @GetMapping("/getAllProductByCategoryId/{id}")
    public ResponseEntity<?> getAllProductByCategoryId (@PathVariable Long id){
        try{
            Set<Product> productResponse = iProductService.findAllByCategoryId(id);
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.success, productResponse,null));

        }catch(NotFoundException ex){
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.NOT_FOUND.value(),Message.failure, null, ex.getMessage()));

        }
    }

    @PutMapping("/{id}")
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> update (@PathVariable Long id,@RequestBody ProductDto productDto){
        try{
            Product productRequest = modelMapper.map(productDto, Product.class);
            Boolean isUpdate = iProductService.update(productRequest,id);
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.success,isUpdate,null));
        }catch(NotFoundException ex){
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.NOT_FOUND.value(),Message.failure, null, ex.getMessage()));

        }
    }

    @DeleteMapping("/{id}")
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try{
            Boolean isDelete = iProductService.delete(id);
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.OK.value(),Message.success,isDelete,null));

        }catch(NotFoundException ex){
            return ResponseEntity.ok().body(new DataResponse(HttpStatus.NOT_FOUND.value(),Message.failure, null, ex.getMessage()));
        }
    }
}
