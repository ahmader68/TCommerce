package com.intechdev.tcommerce.Message;

import com.intechdev.tcommerce.Data.TCommerceDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class MessagePresenter implements MessageContract.Presenter {

    private TCommerceDataSource tCommerceDataSource;
    private MessageContract.View view;
    private CompositeDisposable compositeDisposable;

    MessagePresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(MessageContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
