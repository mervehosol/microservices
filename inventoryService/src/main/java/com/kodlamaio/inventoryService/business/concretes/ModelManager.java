package com.kodlamaio.inventoryService.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryService.business.abstracts.ModelService;
import com.kodlamaio.inventoryService.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryService.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryService.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryService.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryService.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventoryService.dataAccess.ModelRepository;
import com.kodlamaio.inventoryService.entities.Model; //uniqe ıdentiy file

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

	private ModelMapperService modelMapperService;
	private ModelRepository modelRepository;

	@Override
	public List<GetAllModelsResponse> getAll() {
		List<Model> models = this.modelRepository.findAll();
		List<GetAllModelsResponse> response = models.stream()
				.map(model -> this.modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public CreateModelResponse add(CreateModelRequest createModelRequest) {
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());

		this.modelRepository.save(model);
		CreateModelResponse createModelResponse = this.modelMapperService.forResponse().map(model, CreateModelResponse.class);

		return createModelResponse;
	}

	@Override
	public UpdateModelResponse update(UpdateModelRequest updateModelRequest) {
		Model model=this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
		model.setId(UUID.randomUUID().toString());

		this.modelRepository.save(model);
		UpdateModelResponse updateModelResponse=this.modelMapperService.forResponse().map(model, UpdateModelResponse.class);
		return updateModelResponse;
	}

	@Override
	public void delete(String id) {
		checkIfModelExistsById(id);
		modelRepository.deleteById(id);

	}

	@Override
	public GetModelResponse getById(String id) {
		checkIfModelExistsById(id);
		Model model = modelRepository.findById(id).get();
		GetModelResponse getModelResponse = this.modelMapperService.forResponse().map(model, GetModelResponse.class);
		return getModelResponse;
	}
	private void checkIfModelExistsById(String id) {
		var result = this.modelRepository.findById(id).orElse(null); /////***
		if (result == null) {
			throw new BusinessException("MODEL.NO.EXISTS");
		}
	}

}
