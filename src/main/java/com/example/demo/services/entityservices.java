package com.example.demo.services;

import java.util.List;


import com.example.demo.payload.entitypayload;
public interface entityservices {
	entitypayload addusers(entitypayload adu);
	entitypayload updateusers(entitypayload adu,int id);
	void deleteusers(int id);
	List<entitypayload>getallusers();
	entitypayload getbyid(int id);
}
