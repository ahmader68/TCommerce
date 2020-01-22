package com.intechdev.tcommerce.Profile;

import com.intechdev.tcommerce.Data.TCommerceDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    ProfilePresenter(TCommerceDataSource tCommerceDataSource) {
        this.tCommerceDataSource = tCommerceDataSource;
    }

    @Override
    public void attachView(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
