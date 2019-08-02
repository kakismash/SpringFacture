package com.kaki.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.kaki.springboot.app.models.entity.Facture;

public interface IFactureDao extends CrudRepository<Facture, Long>{

	
}
