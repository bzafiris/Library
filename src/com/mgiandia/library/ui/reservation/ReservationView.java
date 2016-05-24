package com.mgiandia.library.ui.reservation;

import com.mgiandia.library.ui.View;

public interface ReservationView extends View {

	public void setPresenter(ReservationPresenter reservationPresenter);

	public String getBookISBN();

	public void setBookTitle(String string);

	public void setReserveActionEnabled(boolean b);

	public int getBorrowerNo();

	public void setBorrowerFirstName(String string);

	public void setBorrowerLastName(String string);

}
