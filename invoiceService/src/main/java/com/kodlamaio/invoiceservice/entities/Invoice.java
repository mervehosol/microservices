package com.kodlamaio.invoiceservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "rentalId")
	private String rentalId;
	@Column(name = "paymentId")
    private String paymentId;

}

