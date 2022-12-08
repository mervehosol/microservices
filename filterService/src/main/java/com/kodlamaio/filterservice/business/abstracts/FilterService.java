package com.kodlamaio.filterservice.business.abstracts;

import java.util.List;

import com.kodlamaio.common.events.filter.CarCreatedEvent;
import com.kodlamaio.common.events.filter.CarDeletedEvent;
import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.responses.GetFiltersResponse;

public interface FilterService {
	 List<GetAllFiltersResponse> getAll();
	    List<GetAllFiltersResponse> getByBrandName(String brandName);
	    List<GetAllFiltersResponse> getByModelName(String modelName);
	    GetFiltersResponse getByPlate(String plate);
	    List<GetAllFiltersResponse> getByDailyPrice(double min,double max);
		List<GetAllFiltersResponse> getByModelYear(int min, int max);
	    
	    
	    // Consumer service	    
	    
	 
	    
	    void addCar(CarCreatedEvent carCreatedEvent);
	    void deleteCar(CarDeletedEvent carDeletedEvent);
	    
	    void deleteAllByBrandId(String brandId);
	    void deleteAllByModelId(String modelId);
	}
