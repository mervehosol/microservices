package com.kodlamaio.invoiceservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InvoiceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServiceApplication.class, args);
	}
//	@Bean
//	public ModelMapper getModelMapper() {
//		return new ModelMapper();
//	}
//	@Bean
//	public ModelMapperService getModelMapperService(ModelMapper modelMapper) {
//		return new ModelMapperManager(modelMapper);
//	}

}
