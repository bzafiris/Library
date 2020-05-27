package com.mgiandia.library.view.reservation;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<P> extends ViewModel {

    protected abstract P createPresenter();

    public abstract P getPresenter();
}
