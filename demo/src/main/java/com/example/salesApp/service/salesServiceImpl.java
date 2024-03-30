package com.example.salesApp.service;




import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.salesApp.entity.client;
import com.example.salesApp.entity.product;
import com.example.salesApp.entity.sales;
import com.example.salesApp.models.salesModel;
import com.example.salesApp.repository.salessRepository;



@Service
public class salesServiceImpl  implements salesService{

	@Autowired
	private salessRepository salesRepository;
	

	@Override
	public sales createSales(salesModel model) {
		ModelMapper modelMapper = new ModelMapper();
		sales salesEntity = modelMapper.map(model, sales.class);	
		if (salesEntity.getClients() == null) {
			salesEntity.setClients(null);

		}else {
		for (int i = 0; i < salesEntity.getClients().size(); i++) {
			client clientEntity = salesEntity.getClients().get(i);
			clientEntity.setSales(salesEntity);
			
//			if (clientEntity.getProduct() == null) {
//				clientEntity.setProducts(null);
//
//			}else {
//				for (int j = 0; i < clientEntity.getProduct().size(); j++) {
//					product productEntity = clientEntity.getProduct().get(i);
//					productEntity.setClient(clientEntity);
//					clientEntity.getProduct().set(j, productEntity);
//	
//				}
//			}
			
			salesEntity.getClients().set(i, clientEntity);


		}}
		sales salesEntity2=salesRepository.save(salesEntity);

		return salesEntity2;
	}


	@Override
	public List<sales> getSales(int page, int limit) {
		if (page > 0)
			page = page - 1;
		Pageable pagableRequest = PageRequest.of(page, limit);
		Page<sales> salessPage = salesRepository.findAll(pagableRequest);
		List<sales> saless = salessPage.getContent();



		return saless;		
	}


	@Override
	public sales getSalesById(long userId) {
		sales salesEntity = salesRepository.findSalesById(userId);

		return salesEntity;		
	}


	@Override
	public sales updateSales(long salesId, sales sales) {
		sales returnValue = new sales();
		sales salesEntity = salesRepository.findSalesById(salesId);

		salesEntity.setName(sales.getName());
		salesEntity.setTotal(sales.getTotal());

		sales salesEntityupdated = salesRepository.save(salesEntity);


		return salesEntityupdated;
	}



	@Override
	public void deleteSales(long salesId) {
		sales salesEntity = salesRepository.findSalesById(salesId);

		salesRepository.delete(salesEntity);
	}










}
