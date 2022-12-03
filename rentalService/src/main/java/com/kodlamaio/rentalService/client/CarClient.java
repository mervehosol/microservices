package com.kodlamaio.rentalService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(value = "carclient", url = "http://localhost:9011/stock/api/cars") //gateway üzerinden soruyoryz
public interface CarClient {
	 //    @PostMapping("inventory-service/api/v1/cars/checkIfCarAvailable/{id}")
	
    @RequestMapping(method = RequestMethod.GET, value = "/{carId}")//sorumuzu nereye sorucaz
    @Headers(value = "Content-Type: application/json")
    void getIfByCarId(@PathVariable String  carId); //sorumuz
    
}


