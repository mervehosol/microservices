package com.kodlamaio.inventoryService.business.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelResponse {
	private String id;
	private String name;
	private String brandId;
}
