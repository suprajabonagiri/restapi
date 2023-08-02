package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.entity;

@Repository
	public interface repo extends CrudRepository<entity, Integer>
	{
}
