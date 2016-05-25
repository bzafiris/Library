package com.mgiandia.library.memorydao;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.domain.Reservation;

public class ReservationDAOMemory implements ReservationDAO {

	static List<Reservation> reservations = new ArrayList<>();
	
	@Override
	public void save(Reservation reservation) {
		
		reservations.add(reservation);
		
	}

	@Override
	public List<Reservation> findAll() {
		return new ArrayList<>(reservations);
	}

}
