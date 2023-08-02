package com.example.demo.services.serviceimple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptionhandling.ResourceNotFoundException;
import com.example.demo.Model.entity;
import com.example.demo.payload.entitypayload;
import com.example.demo.services.entityservices;
import com.example.demo.Repository.repo;
@Service
public class entityservicesimple implements entityservices {
	
	@Autowired
	repo repo;
	@Autowired
	ModelMapper modelmapper;
	@Override
	public entitypayload addusers(entitypayload ent) {
		entity e=this.dto_entity(ent);//
		entity saveentity=this.repo.save(e);
		return this.entity_dto(saveentity);
	
	}

	public entitypayload updateusers(entitypayload ent, int id) {
	entity e=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("entity","id",id));	
	e.setUsername(ent.getUsername());
	e.setEmail(ent.getEmail());
	e.setPassword(ent.getPassword());
	entity e1=this.repo.save(e);
	entitypayload ep=this.entity_dto(e1);
	return ep;
	}

	@Override
	public void deleteusers(int id) {
		entity e=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("entity","id",id));
		this.repo.delete(e);
     
	}

	@Override
	public List<entitypayload> getallusers() {
		List<entity> ent=(List<entity>)this.repo.findAll();
		List<entitypayload> ep=ent.stream().map(entity -> this.entity_dto(entity)).collect(Collectors.toList());
		return ep;
	}

	@Override
	public entitypayload getbyid(int id) {
		entity e =this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("entity","id", id));
			return this.entity_dto(e);
			
		
	}

	//data transfer from original layer to duplicate layer
		public entity dto_entity(entitypayload ep) {
			entity e=this.modelmapper.map(ep, entity.class);
			return e;
		}
		//data transfer from duplicate layer to original layer
		public entitypayload entity_dto(entity ent) {
			entitypayload entity=this.modelmapper.map(ent, entitypayload.class);
			return entity;
		}
}
