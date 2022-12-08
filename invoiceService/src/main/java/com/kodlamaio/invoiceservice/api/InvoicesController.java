package com.kodlamaio.invoiceservice.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.responses.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.UpdateInvoiceResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/invoices")
public class InvoicesController {
    private final InvoiceService invoiceService;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id) {
        return invoiceService.getById(id);
    }

    @PostMapping
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest request) {
        return invoiceService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@Valid @RequestBody UpdateInvoiceRequest request, @PathVariable String id) {
        return invoiceService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        invoiceService.delete(id);
    }
}