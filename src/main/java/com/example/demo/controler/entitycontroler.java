package com.example.demo.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.demo.services.entityservices;
import com.example.demo.Exceptionhandling.ApiResponse;
import com.example.demo.payload.entitypayload;
@RestController
@RequestMapping("/api/v1")

public class entitycontroler {
	@Autowired
	entityservices service;
	@PostMapping("/insert")
	public ResponseEntity<entitypayload>addusers(@Valid @RequestBody entitypayload ent) {
		entitypayload entity=this.service.addusers(ent);
		return new ResponseEntity<>(entity,HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<entitypayload>updateusers(@Valid @PathVariable int id,@RequestBody entitypayload pay){
		entitypayload entity=this.service.updateusers(pay,id);
		return ResponseEntity.ok(entity);
	}
	@DeleteMapping("/delete/{id}")
	 public ResponseEntity<ApiResponse>deleteusers(@Valid @PathVariable int id){
		 this.service.deleteusers(id);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("id deleted successfully",true),HttpStatus.OK);
	 }
	@GetMapping("/getall")
	public ResponseEntity<List<entitypayload>> getall(){
		return ResponseEntity.ok(this.service.getallusers());
		
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<entitypayload>getbyid(@Valid @PathVariable int id) {
		return ResponseEntity.ok(this.service.getbyid(id));
	}
	
}
