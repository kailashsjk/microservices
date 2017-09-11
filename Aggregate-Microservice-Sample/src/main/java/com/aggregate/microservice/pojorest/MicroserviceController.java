package com.aggregate.microservice.pojorest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/microservice")
public class MicroserviceController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/ReadAll")
	public Iterable<Object> readAllMongoDB() {
		
		List<Object> mongodb=new ArrayList<Object>();
		List<Object> mySQL=new ArrayList<Object>();
		List<Object> merge=new ArrayList<Object>();
		try{				
			mongodb = Arrays.asList(restTemplate.getForObject("http://localhost:8083/booking/read-all", Object[].class));
			mySQL = Arrays.asList(restTemplate.getForObject("http://localhost:8082/booking/readAll", Object[].class));
			merge = new ArrayList<Object>(mongodb);
			merge.addAll(mySQL);
		}catch(Exception e){
			System.out.println("Connection Exception"+e.getMessage());
		}
		return merge;
		
	}
		
}
