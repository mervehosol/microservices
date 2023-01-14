package com.kodlamaio.filterservice.business.abstracts;

import java.util.List;

import com.kodlamaio.common.events.inventory.brand.BrandUpdatedEvent;
import com.kodlamaio.common.events.inventory.car.CarCreatedEvent;
import com.kodlamaio.common.events.inventory.car.CarDeletedEvent;
import com.kodlamaio.common.events.inventory.car.CarUpdatedEvent;
import com.kodlamaio.common.events.inventory.model.ModelUpdatedEvent;
import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.responses.GetFilterResponse;

public interface FilterService {
	 List<GetAllFiltersResponse> getAll();
	    List<GetAllFiltersResponse> getByBrandName(String brandName);
	    List<GetAllFiltersResponse> getByModelName(String modelName);
	    GetFilterResponse getByPlate(String plate);
	    List<GetAllFiltersResponse> getByDailyPrice(double min,double max);
	    List<GetAllFiltersResponse> getByModelYear(int modelYear);
	    
	    void addCar(CarCreatedEvent carCreatedEvent);
		void deleteCar(CarDeletedEvent carDeletedEvent);
		void updateCar(CarUpdatedEvent carUpdatedEvent);
		
		void updateBrand(BrandUpdatedEvent brandUpdatedEvent);
		void updateModel(ModelUpdatedEvent modelUpdatedEvent);
	}
