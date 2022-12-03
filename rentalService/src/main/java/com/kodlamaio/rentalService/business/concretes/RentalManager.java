package com.kodlamaio.rentalService.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.get.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;
import com.kodlamaio.rentalService.client.CarClient;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entities.Rental;
import com.kodlamaio.rentalService.kafka.RentalProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
	private CarClient client;


	@Override
	public List<GetAllRentalsResponse> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
		this.client.getIfByCarId(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		rental.setTotalPrice(createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays());
		rental.setDataStarted(LocalDateTime.now());

		 this.rentalRepository.save(rental);

		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(createRentalRequest.getCarId());
		rentalCreatedEvent.setMessage("Rental Created");

		this.rentalProducer.sendMessage(rentalCreatedEvent);

		CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental,
				CreateRentalResponse.class);
		return createRentalResponse;
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
		this.client.getIfByCarId(updateRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		rental.setCarId(updateRentalRequest.getCarId());
		rental.setDailyPrice(updateRentalRequest.getDailyPrice());
		rental.setDataStarted(LocalDateTime.now());

		Rental rentalUpdated = this.rentalRepository.save(rental);

		RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
		rentalUpdatedEvent.setCarId(rentalUpdated.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");

		this.rentalProducer.sendMessage(rentalUpdatedEvent);

		UpdateRentalResponse updatedRentalResponse = this.modelMapperService.forResponse().map(rental,
				UpdateRentalResponse.class);

		return updatedRentalResponse;

	}

	@Override
	public GetRentalResponse getById(String carId) {
		checkIfRentalExistsById(carId);
		Rental rental = rentalRepository.findById(carId).get();
		GetRentalResponse getRentalResponse = this.modelMapperService.forResponse().map(rental,
				GetRentalResponse.class);
		return getRentalResponse;

	}

	private void checkIfRentalExistsById(String carId) {
		var result = this.rentalRepository.findById(carId);
		if (result == null) {
			throw new BusinessException("RENTAL.NO.EXISTS");
	    }
	
	
 }
}
