package com.example.salesApp.service;




import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.salesApp.entity.product;
import com.example.salesApp.models.productModel;
import com.example.salesApp.repository.productRepository;


@Service
public class productServiceImpl  implements productService{

	@Autowired
	private productRepository productRepository;
	

	@Override
	public product createProduct(productModel model) {
		ModelMapper modelMapper = new ModelMapper();
		product productEntity = modelMapper.map(model, product.class);	
		product productEntity2=productRepository.save(productEntity);

		return productEntity2;
	}


	@Override
	public List<product> getproducts(int page, int limit) {
		if (page > 0)
			page = page - 1;
		Pageable pagableRequest = PageRequest.of(page, limit);
		Page<product> productsPage = productRepository.findAll(pagableRequest);
		List<product> products = productsPage.getContent();



		return products;		
	}


	@Override
	public product getproductById(long userId) {
		product productEntity = productRepository.findProductById(userId);

		return productEntity;		
	}


	@Override
	public product updateProduct(long productId, product product) {
		product returnValue = new product();
		product productEntity = productRepository.findProductById(productId);

		productEntity.setName(product.getName());
		productEntity.setDescription(product.getDescription());
		productEntity.setCategory(product.getCategory());
		productEntity.setPrice(product.getPrice());
		productEntity.setQuantity(product.getQuantity());

		product productEntityupdated = productRepository.save(productEntity);


		return productEntityupdated;
	}



	@Override
	public void deleteProduct(long productId) {
		product productEntity = productRepository.findProductById(productId);

		productRepository.delete(productEntity);
	}

}
