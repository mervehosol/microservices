package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryService.business.abstracts.CarService;
import com.kodlamaio.inventoryService.business.abstracts.ModelService;
import com.kodlamaio.inventoryService.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryService.dataAccess.CarRepository;
import com.kodlamaio.inventoryService.entities.Car;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private ModelService modelService;

	@Override
	public List<GetAllCarsResponse> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public CreateCarResponse add(CreateCarRequest createCarRequest) {
		checkIfModelExistsByModelId(createCarRequest.getModelId());
		checkIfCarExistsByPlate(createCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());

		this.carRepository.save(car);

		CreateCarResponse createCarResponse = this.modelMapperService.forResponse().map(car, CreateCarResponse.class);
		return createCarResponse;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest updateCarRequest) {
		checkIfModelExistsByModelId(updateCarRequest.getModelId());
		checkIfCarExistsById(updateCarRequest.getId());
		checkIfCarExistsByPlate(updateCarRequest.getPlate());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		car.setId(UUID.randomUUID().toString());

		this.carRepository.save(car);

		UpdateCarResponse updateCarResponse = this.modelMapperService.forResponse().map(car, UpdateCarResponse.class);
		return updateCarResponse;
	}


	@Override
	public void delete(String id) {
		checkIfCarExistsById(id);
		carRepository.deleteById(id);

	}

	private void checkIfCarExistsById(String id) {
		var result = carRepository.findById(id);
		if (result == null) {
			throw new BusinessException("CAR.NO.EXISTS");
		}
	}

	private void checkIfCarExistsByPlate(String plate) {
		var result = carRepository.findByPlate(plate);
		if (result != null) {
			throw new BusinessException("CAR.EXISTS");
		}
	}

			private void checkIfModelExistsByModelId(String modelId) {
		var result = modelService.getById(modelId);
		if (result == null) {
			throw new BusinessException("MODEL.NO.EX??STS");
		}

	}

			@Override
			public void updateCarState(String carId,int state) {
			Car car= carRepository.findById(carId).get();
			car.setState(state);
			carRepository.save(car);
				
			}

			@Override
			public void getIfByCarId(String carId) {
			Car car=this.carRepository.findById(carId).get();
			if(car.getState()!=1 || car==null) {
				throw new BusinessException("CAR NOT AVA??LABLE");
			}
		}


}
