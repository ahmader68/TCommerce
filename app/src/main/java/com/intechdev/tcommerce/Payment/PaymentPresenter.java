package com.intechdev.tcommerce.Payment;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Data.TCommerceDataSource;

import org.jetbrains.annotations.Contract;

import io.reactivex.disposables.CompositeDisposable;

public class PaymentPresenter implements PaymentContract.Presenter {

    private PaymentContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    PaymentPresenter(TCommerceDataSource tCommerceDataSource) {
        this.tCommerceDataSource = tCommerceDataSource;
    }

    @Override
    public void attachView(PaymentContract.View view) {
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
