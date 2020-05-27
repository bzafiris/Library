package com.mgiandia.library.view.framework;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<P extends BasePresenter> extends ViewModel {

    protected P presenter;

    protected BaseViewModel(){
        presenter = createPresenter();
    }

    protected abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // avoid leaking activity for any reason
        presenter.clearView();
    }
}
