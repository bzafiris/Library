package com.mgiandia.library.dao;

import com.mgiandia.library.domain.Reservation;

public interface ReservationDAO {

    void save(Reservation reservation);

    Reservation find(int borrowerId, int bookId);

}
