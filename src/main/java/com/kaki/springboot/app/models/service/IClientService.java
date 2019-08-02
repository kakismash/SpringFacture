package com.kaki.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kaki.springboot.app.models.entity.Client;
import com.kaki.springboot.app.models.entity.Facture;
import com.kaki.springboot.app.models.entity.Product;

	public interface IClientService {

	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable page);
	
	public void save(Client client);
	
	public Client findOne(Long id);
	
	public void delete(Long id);
	
	public List<Product> findByName(String term);
	
	public void saveFacture(Facture facture);
	
	public Product findProductById(Long id);
	
	public Facture findFactureById(Long id);
	
	public void deleteFacture(long id);
}
