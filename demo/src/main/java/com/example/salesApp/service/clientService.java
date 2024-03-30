package com.example.salesApp.service;

import java.util.List;

import com.example.salesApp.entity.client;
import com.example.salesApp.models.clientModel;


public interface clientService {
	
	client createClient(clientModel model);
    List<client> getClients(int page,int limit);
    client getClientById(long userId);
    client updateClient(long clientId,client clientModel);
    void deleteClient(long clientId);



}
