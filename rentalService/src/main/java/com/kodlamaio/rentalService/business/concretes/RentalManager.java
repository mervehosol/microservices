package com.kodlamaio.rentalService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.InvoiceCreatedEvent;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryService.business.responses.get.GetCarResponse;
import com.kodlamaio.rentalService.business.abstracts.RentalService;
import com.kodlamaio.rentalService.business.constants.Messages;
import com.kodlamaio.rentalService.business.requests.create.CreatePaymentRequest;
import com.kodlamaio.rentalService.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalService.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalService.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalService.business.responses.get.GetAllRentalsResponse;
import com.kodlamaio.rentalService.business.responses.get.GetRentalResponse;
import com.kodlamaio.rentalService.business.responses.update.UpdateRentalResponse;
import com.kodlamaio.rentalService.client.InventoryClient;
import com.kodlamaio.rentalService.client.PaymentClient;
import com.kodlamaio.rentalService.dataAccess.RentalRepository;
import com.kodlamaio.rentalService.entities.Rental;
import com.kodlamaio.rentalService.kafka.InvoiceProducer;
import com.kodlamaio.rentalService.kafka.RentalProducer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private RentalProducer rentalProducer;
	private PaymentClient paymentClient;
	private InventoryClient inventoryClient;
	private InvoiceProducer invoiceProducer;

	@Override
	public List<GetAllRentalsResponse> getAll() {
		List<Rental> rentals = rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public CreateRentalResponse add(CreateRentalRequest createRentalRequest,
			CreatePaymentRequest createPaymentRequest) {
		checkIfRentalExistsState(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(UUID.randomUUID().toString());
		double totalPrice = createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays();
		rental.setTotalPrice(totalPrice);

		paymentClient.paymentReceived(createPaymentRequest.getCardNo(), createPaymentRequest.getCardHolder(),
				createPaymentRequest.getCvv(), createPaymentRequest.getCardDate(), rental.getTotalPrice());

		this.rentalRepository.save(rental);

		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
		rentalCreatedEvent.setCarId(createRentalRequest.getCarId());
		rentalCreatedEvent.setMessage("Rental Created");
		this.rentalProducer.sendMessage(rentalCreatedEvent);

		InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
		invoiceCreatedEvent.setCarId(rental.getCarId());
		invoiceCreatedEvent.setFullName(createPaymentRequest.getCardHolder());
		invoiceCreatedEvent.setDailyPrice(createRentalRequest.getDailyPrice());
		invoiceCreatedEvent.setTotalPrice(rental.getTotalPrice());
		invoiceCreatedEvent.setRentedForDays(createRentalRequest.getRentedForDays());
		invoiceCreatedEvent.setRentedDate(rental.getDateStarted());
		invoiceProducer.sendMessage(invoiceCreatedEvent);

		CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental,
				CreateRentalResponse.class);
		return createRentalResponse;
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
		checkIfRentalExistsId(updateRentalRequest.getId());
		RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();

		Rental rental = this.rentalRepository.findById(updateRentalRequest.getId()).get();
		rentalUpdatedEvent.setOldCarId(rental.getCarId());

		rental.setCarId(updateRentalRequest.getCarId());
		rental.setDailyPrice(updateRentalRequest.getDailyPrice());
		rental.setRentedForDays(updateRentalRequest.getRentedForDays());
		double totalPrice = updateRentalRequest.getDailyPrice() * updateRentalRequest.getRentedForDays();
		rental.setTotalPrice(totalPrice);

		Rental rentalUpdated = this.rentalRepository.save(rental);
		rentalUpdatedEvent.setNewCarId(rentalUpdated.getCarId());
		rentalUpdatedEvent.setMessage("Rental Updated");
		rentalProducer.sendMessage(rentalUpdatedEvent);

		UpdateRentalResponse updatedRentalResponse = this.modelMapperService.forResponse().map(rental,
				UpdateRentalResponse.class);

		return updatedRentalResponse;

	}
	@Override
	public GetRentalResponse getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		checkIfRentalExistsId(id);
		this.rentalRepository.deleteById(id);
	}
	private void checkIfRentalExistsId(String id) {
		Rental rental = this.rentalRepository.findById(id).get();
		if (rental == null) {
			throw new BusinessException(Messages.RentalIdNotFound);
		}
	}

	private void checkIfRentalExistsState(@NotNull String carId) {// arabanın kiralanıp kiralanmadığı kontrolü
		GetCarResponse getCarResponse = inventoryClient.getByCarId(carId);
		if (getCarResponse.getState() == 2) {// kiralanmış arabaysa
			throw new BusinessException(Messages.CarHired);
		}

	}
	
	

}
