package com.example.salesApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesApp.entity.product;
import com.example.salesApp.models.OperationResult;
import com.example.salesApp.models.RequestOperationName;
import com.example.salesApp.models.RequestOperationStatus;
import com.example.salesApp.models.productModel;
import com.example.salesApp.models.productRespose;

@RestController
@RequestMapping("products") // http://localhost:8081/products
public class productController {

	@Autowired
	com.example.salesApp.service.productService productService;
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public productRespose createProduct(@RequestBody productModel producttModel)    {
		
		productRespose returnValue = new productRespose();
		product product = productService.createProduct(producttModel);
		
		ModelMapper modelMapper = new ModelMapper();
	   returnValue = modelMapper.map(product, productRespose.class);
		return returnValue;
	}
	
	org.slf4j.Logger logger= LoggerFactory.getLogger(productController.class);
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<productRespose> getComponents(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		logger.trace("Fatal error");
		List<productRespose> returnValue = new ArrayList<>();
		List<product> products = productService.getproducts(page, limit);		
		for (product product : products) {
			ModelMapper model = new ModelMapper();
			productRespose trueValue = model.map(product, productRespose.class);
			returnValue.add(trueValue);
		}

		return returnValue;
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public productRespose GetProduct(@PathVariable long id) {
		
		product productEntity=productService.getproductById(id);
		
		ModelMapper model = new ModelMapper();
		productRespose trueValue = model.map(productEntity, productRespose.class);
		
		return trueValue;
	}
	
	@PutMapping(path = "/{id}", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE })	
	public productRespose updateComponent(@PathVariable long id, @RequestBody productModel productModel) {
		productRespose returnValue = new productRespose(); // to return information to the response
		product productEntity=new product();
		BeanUtils.copyProperties(productModel, productEntity); // to populate request body with DTO source and target
		// action
		product productEntityupdated = productService.updateProduct(id, productEntity);
		ModelMapper model=new ModelMapper();
		productRespose returnvalue = model.map(productEntityupdated, productRespose.class);

		return returnvalue;	}
	
	@DeleteMapping(path = "/{id}",  produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public OperationResult deleteProduct(@PathVariable long id) {
		OperationResult returnValue = new OperationResult();
		returnValue.setOperationName(RequestOperationName.DELETE.name());

		productService.deleteProduct(id);

		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		return returnValue;	}
}
