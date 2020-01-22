package com.intechdev.tcommerce.Downloads;

import com.intechdev.tcommerce.Data.TCommerceDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class DownloadsPresenter implements DownloadsContract.Presenter{

    private DownloadsContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable;

    DownloadsPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(DownloadsContract.View view) {
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
