package com.mgiandia.library.ui;

import org.junit.Test;

import com.mgiandia.library.ui.reservation.ReservationJFrame;
import com.mgiandia.library.ui.reservation.ReservationPresenter;

public class ReservationPresenterTest {

	@Test
	public void test() {
	
		ReservationPresenter presenter = new ReservationPresenter(new ReservationJFrame());
		presenter.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
