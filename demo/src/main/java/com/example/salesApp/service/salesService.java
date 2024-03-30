package com.example.salesApp.service;

import java.util.List;
import java.util.Map;

import com.example.salesApp.entity.sales;
import com.example.salesApp.models.salesModel;



public interface salesService {
	
	sales createSales(salesModel model);
    List<sales> getSales(int page,int limit);
    sales getSalesById(long userId);
    sales updateSales(long salesId,sales sales);
    void deleteSales(long salesId);



}
