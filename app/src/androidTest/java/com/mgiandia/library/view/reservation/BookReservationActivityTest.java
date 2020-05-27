package com.mgiandia.library.view.reservation;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.mgiandia.library.R;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.dao.ReservationDAO;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.memorydao.ReservationDAOMemory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.runner.Version.id;
import static org.hamcrest.CoreMatchers.is;


public class BookReservationActivityTest {

    @Rule
    public ActivityTestRule<BookReservationActivity> mActivityRule = new ActivityTestRule<>(
            BookReservationActivity.class);


    @Before
    public void setUp() throws Exception {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();
    }

    @Test
    public void makeSuccessfulReservation() throws InterruptedException {

        onView(withId(R.id.edt_book_title)).perform(clearText());
        onView(withId(R.id.edt_book_title)).perform(typeText("The Odyssey"));

        onView(withId(R.id.btn_search_book)).perform(click());

        Thread.sleep(2000);

        onView(withId(R.id.btn_select_book)).perform(click());

        Thread.sleep(2000);

        onView(withId(R.id.edt_borrower_id)).perform(clearText());
        onView(withId(R.id.edt_borrower_id)).perform(typeText("2"), closeSoftKeyboard());

        onView(withId(R.id.btn_reserve_book)).perform(click());

        ReservationDAOMemory dao = new ReservationDAOMemory();
        assertThat(dao.findAll().size() , is(1));

    }

}