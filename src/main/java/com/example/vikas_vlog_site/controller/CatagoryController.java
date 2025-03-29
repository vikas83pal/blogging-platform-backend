package com.example.vikas_vlog_site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.vikas_vlog_site.payload.categoryDto;
import com.example.vikas_vlog_site.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/api/catagory")
public class CatagoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<categoryDto> createCatagory(@Valid @RequestBody categoryDto categoryDto){
        categoryDto categoryDto2  = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto2, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<categoryDto> updateCategory(@Valid @RequestBody categoryDto categoryDto, @PathVariable int id){
        categoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, id);

        if(updateCategoryDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return new ResponseEntity<>(updateCategoryDto,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<categoryDto> getCategoryById(@PathVariable int id){
        categoryDto catId = this.categoryService.getCategoryById(id);

        if(catId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return new ResponseEntity<categoryDto>(catId, HttpStatus.FOUND);
        }
    }

    @GetMapping("/catagories")
    public ResponseEntity<List<categoryDto>> getAllCatagories(){
        List<categoryDto> catogories = this.categoryService.getAllCatagories();

        if(catogories == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(catogories);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatagoryById(int id){
        categoryDto cat = this.categoryService.getCategoryById(id);
        if(cat == null){
            return  ResponseEntity.notFound().build();
        }else{
            Map<String, String> response = new HashMap<>();
            response.put("Message", id + "catagory deleted");
            return ResponseEntity.ok(response); 
        }
    }

    @DeleteMapping("/catagories")
    public ResponseEntity<?> deleteAllCatagories(){
        this.categoryService.deleteAllCatagories();
        Map<String, String> response = new HashMap<>();
        response.put("Message", "All catagories are deleted");
        return ResponseEntity.ok(response);
    }


}
