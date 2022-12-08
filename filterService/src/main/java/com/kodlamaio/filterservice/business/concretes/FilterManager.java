package com.kodlamaio.filterservice.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.filter.CarCreatedEvent;
import com.kodlamaio.common.events.filter.CarDeletedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.responses.GetFiltersResponse;
import com.kodlamaio.filterservice.dataAccess.FilterRepository;
import com.kodlamaio.filterservice.entities.Filter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService{
	private  FilterRepository filterRepository;
    private  ModelMapperService modelMapper;
	
	@Override
	public List<GetAllFiltersResponse> getAll() {
        List<Filter> filters = filterRepository.findAll();

        List<GetAllFiltersResponse> responses = filters.stream().map(filter -> this.modelMapper.forRequest().map(filter, GetAllFiltersResponse.class))        		
       				.collect(Collectors.toList());
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByBrandName(String brandName) {
		 List<Filter> filters = this.filterRepository.findAll();
		 List<GetAllFiltersResponse> responses = filters.stream().map(filter -> this.modelMapper.forRequest().map(filter, GetAllFiltersResponse.class))
				 .collect(Collectors.toList());
				 
				 
			return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByModelName(String modelName) {
		List<Filter> filters = filterRepository.findByModelNameIgnoreCase(modelName);
		List<GetAllFiltersResponse> responses=filters.stream().map(filter-> this.modelMapper.forRequest().map(filter, GetAllFiltersResponse.class))	
				.collect(Collectors.toList());
				return responses;
	}

	@Override
	public GetFiltersResponse getByPlate(String plate) {
		Filter filter= filterRepository.findByPlate(plate);
		GetFiltersResponse getFiltersResponse = this.modelMapper.forResponse().map(filter, GetFiltersResponse.class);
		return getFiltersResponse;
		
	}
	


	@Override
	public void deleteAllByBrandId(String brandId) {
		filterRepository.deleteAllByBrandId(brandId);
	}
	@Override
	public void deleteAllByModelId(String modelId) {
		filterRepository.deleteAllByModelId(modelId);
	}

	@Override
	public void addCar(CarCreatedEvent carCreatedEvent) {
		Filter filter = this.modelMapper.forRequest().map(carCreatedEvent, Filter.class);
		this.filterRepository.save(filter);
	}

	@Override
	public List<GetAllFiltersResponse> getByDailyPrice(double min, double max) {
		List<Filter> filters = this.filterRepository.findAll();
		List<GetAllFiltersResponse> responses = new ArrayList<GetAllFiltersResponse>();
		for (Filter filter : filters) {
			if(filter.getDailyPrice()<max && filter.getDailyPrice()>min) {
				GetAllFiltersResponse response = this.modelMapper.forResponse().map(filter, GetAllFiltersResponse.class);
				responses.add(response);
		
			}
			
		}
		return responses;
	}

	@Override
	public List<GetAllFiltersResponse> getByModelYear(int min, int max) {
		List<Filter> filters = this.filterRepository.findAll();
		List<GetAllFiltersResponse> responses = new ArrayList<GetAllFiltersResponse>();
		for (Filter filter : filters) {
			if(filter.getModelYear()<max && filter.getModelYear()>min) {
				GetAllFiltersResponse response = this.modelMapper.forResponse().map(filter, GetAllFiltersResponse.class);
				responses.add(response);
		
			}
			
		}
		return responses;
	}

	@Override
	public void deleteCar(CarDeletedEvent carDeletedEvent) {
		filterRepository.deleteByCarId(carDeletedEvent.getCarId());
	}
	
	

}
