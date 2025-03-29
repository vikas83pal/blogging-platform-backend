package com.example.vikas_vlog_site.service;

import com.example.vikas_vlog_site.payload.categoryDto;
import com.example.vikas_vlog_site.repository.CategoryRepo;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vikas_vlog_site.exception.ResourceNotFoundException;
import com.example.vikas_vlog_site.model.Catagory;

@Service
public class catagoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public categoryDto createCategory(categoryDto categorySDto) {
		Catagory cat = this.modelMapper.map(categorySDto, Catagory.class);
		Catagory savedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(savedCat, categoryDto.class);
	}

	@Override
	public categoryDto updateCategory(categoryDto categoryDto, Integer id) {
		Catagory cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catogory ", "categoryId ", id));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDesc(categoryDto.getCategoryDesc());

		Catagory updatedCatagory = this.categoryRepo.save(cat);

		return this.modelMapper.map(updatedCatagory, categoryDto.class);
	}

	@Override
	public categoryDto getCategoryById(Integer id) {
		Catagory cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catogory ", "categoryId ", id));
		return this.modelMapper.map(cat, categoryDto.class);
	}

	@Override
	public List<categoryDto> getAllCatagories() {
		List<Catagory> categories = this.categoryRepo.findAll();
		return categories.stream()
				.map(cat -> this.modelMapper.map(cat, categoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public categoryDto deleteCategoryById(Integer id) {
		Catagory cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catogory ", "categoryId ", id));
		this.categoryRepo.delete(cat);
		return this.modelMapper.map(cat, categoryDto.class);
	}

	@Override
	public void deleteAllCatagories() {
		this.categoryRepo.deleteAll();
	}

}
