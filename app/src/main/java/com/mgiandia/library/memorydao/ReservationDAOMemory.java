package com.mgiandia.library.memorydao;

import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOMemory implements ReservationDAO {

    private static List<Reservation> reservations = new ArrayList<>();

    @Override
    public void save(Reservation reservation) {
        if (!reservations.contains(reservation)){
            reservations.add(reservation);
        }
    }

    @Override
    public Reservation find(int borrowerId, int bookId) {
        for(Reservation r: reservations){
            if (r.getBook().getId() == bookId && r.getBorrower().getBorrowerNo() == borrowerId){
                return r;
            }
        }
        return null;
    }

    public List<Reservation> findAll(){
        return reservations;
    }

    public static void reset(){
        reservations.clear();
    }
}
