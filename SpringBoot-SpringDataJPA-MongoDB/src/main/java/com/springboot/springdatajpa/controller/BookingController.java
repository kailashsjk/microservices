
package com.springboot.springdatajpa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springdatajpa.models.Booking;
import com.springboot.springdatajpa.models.BookingRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingRepository bookingRepository;
	/**
	 * GET /create  --> Create a new booking and save it in the database.
	 */
	
	
	@RequestMapping("/create")
	public Booking create(@Valid @RequestBody Booking booking) {
		
		try{		
		
		booking.setTravelDate(new Date());
		
		booking = bookingRepository.save(booking);	
		
		
	}catch(Exception e){
		System.out.println("Connection Exception"+e.getMessage());
	}
	    return booking;
	}
	
	
	/**
	 * GET /read  --> Read a booking by booking id from the database.
	 */
	
	@RequestMapping("/read")
	public Booking read(@Valid @RequestBody Booking booking) {
		Booking book = bookingRepository.findOne(booking.getId());
	
	    return book;
	}

	/**
	 * GET /update  --> Update a booking record and save it in the database.
	 */
	@RequestMapping("/update")
	public Booking update(@Valid @RequestBody Booking book) {
		Booking booking = bookingRepository.findOne(book.getId());
		booking.setPsngrName(book.getPsngrName());
		booking.setDeparture(book.getDeparture());
		booking.setDestination(book.getDestination());
		booking.setTravelDate(new Date());
		booking = bookingRepository.save(booking);		
		
	    return booking;
	}
	
	/**
	 * GET /delete  --> Delete a booking from the database.
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(@Valid @RequestBody Booking book) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		bookingRepository.delete(book.getId());
		
		dataMap.put("booking", book);
		dataMap.put("Status", "Successfully Deleted");
		
	    return dataMap;
	} 
	
	/**
	 * GET /read  --> Read all booking from the database.
	 */
	@RequestMapping("/read-all")
	public List<Booking> readAll() {
	
		List<Booking> bookings = bookingRepository.findAll();		
	    return bookings;
	}
}
