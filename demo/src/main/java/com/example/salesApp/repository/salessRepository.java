package com.example.salesApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.salesApp.entity.sales;

@Repository
	public interface salessRepository extends CrudRepository<sales, Long>,PagingAndSortingRepository<sales, Long>  {
	sales findSalesById(long id);

}
