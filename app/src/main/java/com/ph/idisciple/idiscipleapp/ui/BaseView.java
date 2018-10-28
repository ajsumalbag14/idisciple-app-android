package com.ph.idisciple.idiscipleapp.ui;

public interface BaseView<T extends BasePresenter> {
    void showNoInternetConnection();
    void showTimeoutError();
    void showGenericError();
}
