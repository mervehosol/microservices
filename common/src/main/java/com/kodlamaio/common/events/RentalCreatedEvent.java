package com.kodlamaio.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCreatedEvent {//kiraladığımız aracın eventini buraya gönderiyoruz
	private String message;
	private String carId; //kiralanan aracın ıdsi
}
