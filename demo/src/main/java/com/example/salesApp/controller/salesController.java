package com.example.salesApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesApp.entity.sales;
import com.example.salesApp.models.OperationResult;
import com.example.salesApp.models.RequestOperationName;
import com.example.salesApp.models.RequestOperationStatus;
import com.example.salesApp.models.salesModel;
import com.example.salesApp.models.salesRespose;

@RestController
@RequestMapping("sales") // http://localhost:8081/sales
public class salesController {

	@Autowired
	com.example.salesApp.service.salesService salesService;
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public salesRespose createsales(@RequestBody salesModel salestModel)    {
		
		salesRespose returnValue = new salesRespose();
		sales sales = salesService.createSales(salestModel);
		
		ModelMapper modelMapper = new ModelMapper();
	   returnValue = modelMapper.map(sales, salesRespose.class);
		return returnValue;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<salesRespose> getComponents(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		List<salesRespose> returnValue = new ArrayList<>();
		List<sales> saless = salesService.getSales(page, limit);		
		for (sales sales : saless) {
			ModelMapper model = new ModelMapper();
			salesRespose trueValue = model.map(sales, salesRespose.class);
			returnValue.add(trueValue);
		}

		return returnValue;
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public salesRespose Getsales(@PathVariable long id) {
		
		sales salesEntity=salesService.getSalesById(id);
		
		ModelMapper model = new ModelMapper();
		salesRespose trueValue = model.map(salesEntity, salesRespose.class);
		
		return trueValue;
	}
	
//	@PatchMapping(path = "/{id}", consumes = {
//			MediaType.APPLICATION_JSON_VALUE }, produces = {
//					MediaType.APPLICATION_JSON_VALUE })	
//	public salesRespose updateComponentSpacific(@PathVariable long id,@RequestBody salesModel salesModel) {
//		sales salesEntity=salesService.getSalesById(id);
//		clients clients=new CLientsl
//		salesEntity.setClients(.);
//		BeanUtils.copyProperties(salesModel, salesEntity); // to populate request body with DTO source and target
//		// action
//		sales salesEntityupdated = salesService.updateSales(id, salesEntity);
//		ModelMapper model=new ModelMapper();
//		salesRespose returnvalue = model.map(salesEntityupdated, salesRespose.class);
//
//		return returnvalue;	}
	
	@PutMapping(path = "/{id}", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE })	
	public salesRespose updateComponent(@PathVariable long id, @RequestBody salesModel salesModel) {
		salesRespose returnValue = new salesRespose(); // to return information to the response
		sales salesEntity=new sales();
		BeanUtils.copyProperties(salesModel, salesEntity); // to populate request body with DTO source and target
		// action
		sales salesEntityupdated = salesService.updateSales(id, salesEntity);
		ModelMapper model=new ModelMapper();
		salesRespose returnvalue = model.map(salesEntityupdated, salesRespose.class);

		return returnvalue;	}
	
	@DeleteMapping(path = "/{id}",  produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public OperationResult deletesales(@PathVariable long id) {
		OperationResult returnValue = new OperationResult();
		returnValue.setOperationName(RequestOperationName.DELETE.name());

		salesService.deleteSales(id);

		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		return returnValue;	}
}
