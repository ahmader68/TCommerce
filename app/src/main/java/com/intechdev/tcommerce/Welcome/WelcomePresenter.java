package com.intechdev.tcommerce.Welcome;

import com.intechdev.tcommerce.Data.TCommerceDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class WelcomePresenter implements WelcomeContract.Presenter {

    private WelcomeContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    WelcomePresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
    }

    @Override
    public void attachView(WelcomeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
