package com.intechdev.tcommerce.Shopping_Bag;

import com.intechdev.tcommerce.Data.TCommerceDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class ShoppingBagPresenter implements ShoppingBagContract.Presenter {

    private ShoppingBagContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    ShoppingBagPresenter(TCommerceDataSource tCommerceDataSource) {
        this.tCommerceDataSource = tCommerceDataSource;
    }

    @Override
    public void attachView(ShoppingBagContract.View view) {
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
