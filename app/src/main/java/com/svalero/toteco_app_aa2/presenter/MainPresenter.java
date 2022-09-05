package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.contract.MainContract;
import com.svalero.toteco_app_aa2.model.MainModel;
import com.svalero.toteco_app_aa2.view.MainView;

public class MainPresenter implements MainContract.Presenter {
    private final MainModel model;
    private final MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
        model = new MainModel(view.getApplicationContext());
    }

    @Override
    public void deleteUser() {
        model.deleteUser();
    }
}
