package com.kodlamaio.inventoryService.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.inventoryService.business.abstracts.BrandService;
import com.kodlamaio.inventoryService.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {               //entity,data access,request,response,abstract service,concrete manager,controller
	private BrandService brandService;
	
	@GetMapping
	public List<GetAllBrandsResponse> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping
	public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	@PutMapping
	public UpdateBrandResponse update(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest); 
	}
	@GetMapping("/{id}")
	public GetBrandResponse getById(@PathVariable String id) {
		return this.brandService.getById(id);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {  //rootbalancing y??k dengeleme yap??l??r.//gateway kurunca di??er servisleri art??k ??a????rabiliriz.
		 this.brandService.delete(id);
	}
	
	
}
