package com.example.salesApp.service;

import java.util.List;

import com.example.salesApp.entity.product;
import com.example.salesApp.models.productModel;

public interface productService {
	
	product createProduct(productModel model);
    List<product> getproducts(int page,int limit);
    product getproductById(long userId);
    product updateProduct(long productId,product product);
    void deleteProduct(long productId);



}
