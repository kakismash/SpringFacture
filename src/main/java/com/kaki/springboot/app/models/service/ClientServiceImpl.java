package com.kaki.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaki.springboot.app.models.dao.IClientDao;
import com.kaki.springboot.app.models.dao.IFactureDao;
import com.kaki.springboot.app.models.dao.IProductDao;
import com.kaki.springboot.app.models.entity.Client;
import com.kaki.springboot.app.models.entity.Facture;
import com.kaki.springboot.app.models.entity.Product;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IFactureDao factureDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional
	public void save(Client client) {
		// TODO Auto-generated method stub
		clientDao.save(client);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(Long id) {
		// TODO Auto-generated method stub
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clientDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable page) {
		
		
		return clientDao.findAll(page);
	}

	@Override
	public List<Product> findByName(String term) {
		
		
		return productDao.findByNameLikeIgnoreCase("%" + term + "%");
	}

	@Override
	@Transactional
	public void saveFacture(Facture facture) {
		factureDao.save(facture);
	}

	@Override
	@Transactional(readOnly=true)
	public Product findProductById(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Facture findFactureById(Long id) {
		// TODO Auto-generated method stub
		return factureDao.findById(id).orElse(null);
	}

	@Override
	public void deleteFacture(long id) {
		factureDao.deleteById(id);
	}

}
