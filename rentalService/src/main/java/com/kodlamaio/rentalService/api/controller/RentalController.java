package com.kodlamaio.rentalService.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.get.GetRentalResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalController {  //aracın bilgisini aldım
	private RentalService rentalService;
	
	@GetMapping
	public List<GetAllRentalsResponse> getAll(){
		return this.rentalService.getAll();
	} 
	
	@PostMapping
	public CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
	}
	@GetMapping("/{carId}")
	public GetRentalResponse getById(@PathVariable String carId) {
		return this.rentalService.getById(carId);
	}
}
