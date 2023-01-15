package com.kodlamaio.invoiceservice.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.GetAllInvoicesResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
	
    private InvoiceService invoiceService;

 

    @PostMapping
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return this.invoiceService.add(createInvoiceRequest);
    }
    @GetMapping("/getall")
	public List<GetAllInvoicesResponse> getAll(){
		return this.invoiceService.getAll();
	}
    

}