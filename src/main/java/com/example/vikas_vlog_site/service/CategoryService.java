package com.example.vikas_vlog_site.service;



import java.util.List;

import com.example.vikas_vlog_site.payload.categoryDto;


public interface CategoryService {

    categoryDto createCategory(categoryDto categoryDto);

    categoryDto updateCategory(categoryDto categoryDto, Integer catagoryId);

    categoryDto getCategoryById(Integer catagoryId);

    List<categoryDto> getAllCatagories();

    categoryDto deleteCategoryById(Integer catagoryId);

    void deleteAllCatagories();
}
