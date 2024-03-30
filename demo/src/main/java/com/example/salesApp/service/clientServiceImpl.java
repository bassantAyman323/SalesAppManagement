package com.example.salesApp.service;




import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.salesApp.entity.client;
import com.example.salesApp.entity.product;
import com.example.salesApp.models.clientModel;
import com.example.salesApp.models.clientModel;
import com.example.salesApp.repository.clientRepository;


@Service
public class clientServiceImpl  implements clientService{

	@Autowired
	private clientRepository clientRepository;
	

	@Override
	public client createClient(clientModel model) {
		ModelMapper modelMapper = new ModelMapper();
		client clientEntity = modelMapper.map(model, client.class);	
		
//		if (clientEntity.getProduct() == null) {
//			clientEntity.setProducts(null);
//
//		}else {
//		for (int i = 0; i < clientEntity.getProduct().size(); i++) {
//			product productEntity = clientEntity.getProduct().get(i);
//			productEntity.setClient(clientEntity);
//			clientEntity.getProduct().set(i, productEntity);
//
//
//		}}
		client clientEntity2=clientRepository.save(clientEntity);
		return clientEntity2;
	}


	@Override
	public List<client> getClients(int page, int limit) {
		if (page > 0)
			page = page - 1;
		Pageable pagableRequest = PageRequest.of(page, limit);
		Page<client> clientsPage = clientRepository.findAll(pagableRequest);
		List<client> clients = clientsPage.getContent();
		return clients;		
	}


	@Override
	public client getClientById(long userId) {
		client clientEntity = clientRepository.findClientById(userId);

		return clientEntity;		
	}


	@Override
	public client updateClient(long clientId, client client) {
		client returnValue = new client();
		client clientEntity = clientRepository.findClientById(clientId);
		clientEntity.setFirstName(client.getFirstName());
		clientEntity.setLastName(client.getLastName());
		clientEntity.setAddress(client.getAddress());
		clientEntity.setEmail(client.getEmail());
		clientEntity.setMobile(client.getMobile());
		client clientEntityupdated = clientRepository.save(clientEntity);
		return clientEntityupdated;
	}



	@Override
	public void deleteClient(long clientId) {
		client clientEntity = clientRepository.findClientById(clientId);
		clientRepository.delete(clientEntity);
	}

}
