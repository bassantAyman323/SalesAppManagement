package com.example.salesApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.salesApp.entity.product;

@Repository
	public interface productRepository extends CrudRepository<product, Long>,PagingAndSortingRepository<product, Long>  {
	product findProductById(long id);

}
