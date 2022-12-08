package com.kodlamaio.filterservice.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/filters")
@AllArgsConstructor
public class FilterController {
	private final FilterService service;

    @GetMapping
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/brand")
    public List<GetAllFiltersResponse> getByBrandName(@RequestParam String brandName) {
        return service.getByBrandName(brandName);
    }

    @GetMapping("/model")
    public List<GetAllFiltersResponse> getByModelName(@RequestParam String modelName) {
        return service.getByModelName(modelName);
    }

}