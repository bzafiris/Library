package com.mgiandia.library.ui;

import org.junit.Test;

import com.mgiandia.library.ui.reservation.ReservationJFrame;

public class ReservationTest {

	@Test
	public void test() {
	
		ReservationJFrame frame = new ReservationJFrame();
		frame.setVisible(true);
			
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
