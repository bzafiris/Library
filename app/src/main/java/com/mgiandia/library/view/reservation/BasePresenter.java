package com.mgiandia.library.view.reservation;

public abstract class BasePresenter<V> {
    protected V view;

    public void setView(V view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }
}
