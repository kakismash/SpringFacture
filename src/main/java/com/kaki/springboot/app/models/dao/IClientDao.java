package com.kaki.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kaki.springboot.app.models.entity.Client;

public interface IClientDao extends PagingAndSortingRepository<Client, Long> {
	
	
}
