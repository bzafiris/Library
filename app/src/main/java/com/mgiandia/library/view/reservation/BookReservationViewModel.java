package com.mgiandia.library.view.reservation;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class BookReservationViewModel extends ViewModel {

    BookReservationPresenter presenter;

    public BookReservationViewModel() {
        super();
        Log.d("ViewModel", "ViewModel created");

        // assemble presenter
        presenter = new BookReservationPresenter();
    }

    public BookReservationPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // avoid leaking activity for any reason
        presenter.clearView();
    }
}
