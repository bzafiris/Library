package com.mgiandia.library.dao;

import java.util.List;

import com.mgiandia.library.domain.Reservation;

public interface ReservationDAO {
	
	public void save(Reservation reservation);

	public List<Reservation> findAll();
	
}