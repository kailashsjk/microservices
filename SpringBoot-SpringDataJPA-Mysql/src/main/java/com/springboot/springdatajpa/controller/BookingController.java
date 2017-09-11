package com.springboot.springdatajpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springdatajpa.models.Booking;
import com.springboot.springdatajpa.models.BookingRepository;


/**
 * @author Dinesh.Rajput
 *
 */

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
		booking.setTravelDate(new Date());
		booking = bookingRepository.save(booking);
	    return booking;
	}

	
	/**
	 * GET /read  --> Read a booking by booking id from the database.
	 */
	
	
	@RequestMapping("/read")
	public Booking read(@Valid @RequestBody Booking booking) {
		
		
		System.out.println("bookingId"+booking.getBookingId());
		Booking book = bookingRepository.findOne(booking.getBookingId());
	    return book;
	}
	
	@RequestMapping("/readAll")
	public List<Booking> readAll() {		
		 
		
		Iterable<Booking> book = bookingRepository.findAll();
		List<Booking> list = new ArrayList<Booking>();
	    if(book != null) {
	      for(Booking e: book) {
	        list.add(e);
	      }
	    }
	    return list;
	}


	/**
	 * GET /update  --> Update a booking record and save it in the database.
	 */
	
	
	@RequestMapping("/update")
	public Booking update(@Valid @RequestBody Booking book) {
		Booking booking = bookingRepository.findOne(book.getBookingId());
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
		
		bookingRepository.delete(book.getBookingId());
		dataMap.put("bookingId", book);
		dataMap.put("Status", "Successfully Deleted");
	    return dataMap;
	}

}
