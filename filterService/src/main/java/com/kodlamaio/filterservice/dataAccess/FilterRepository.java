package com.kodlamaio.filterservice.dataAccess;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.filterservice.entities.Filter;

public interface FilterRepository extends MongoRepository<Filter, String> {
	
	List<Filter> findByBrandNameIgnoreCase(String brandName);
    List<Filter> findByModelNameIgnoreCase(String modelName);
    List<Filter> findByPlateIgnoreCase(String plate);
    List<Filter> findByPlateContainingIgnoreCase(String plate);
    List<Filter> findByBrandNameContainingIgnoreCase(String brandName);
    List<Filter> findByModelNameContainingIgnoreCase(String modelName);
    List<Filter> findByModelYear(int modelYear);
    List<Filter> findByModelId(String modelId);
    List<Filter> findByBrandId(String brandId);
    List<Filter> findByState(int state);
    Filter findByPlate(String plate);
    Filter findByCarId(String carId);
    void deleteByCarId(String carId);
    void deleteAllByBrandId(String brandId);
    void deleteAllByModelId(String modelId);

}
