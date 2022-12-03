package com.kodlamaio.rentalService.business.abstracts;

import java.util.List;

import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.get.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;

public interface RentalService {
	List<GetAllRentalsResponse> getAll();
	CreateRentalResponse add(CreateRentalRequest createRentalRequest);
	UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest);
	GetRentalResponse getById(String id);
	
}
