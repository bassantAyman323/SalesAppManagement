package com.example.salesApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.salesApp.entity.client;

@Repository
	public interface clientRepository extends CrudRepository<client, Long>,PagingAndSortingRepository<client, Long>  {
	client findClientById(long id);

}
