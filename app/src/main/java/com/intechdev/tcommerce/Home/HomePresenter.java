package com.intechdev.tcommerce.Home;

import android.content.Context;

import com.intechdev.tcommerce.Classes.Alert_Dialog;
import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.ItemImageSlider;
import com.intechdev.tcommerce.Models.ItemListStock;
import com.intechdev.tcommerce.Models.ProductList;
import com.intechdev.tcommerce.Models.StockListModel;
import com.intechdev.tcommerce.R;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Context context;

    HomePresenter (TCommerceDataSource tCommerceDataSource,Context context){

            this.tCommerceDataSource = tCommerceDataSource;
            this.context = context;

    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getNewProduct(/*String usr , String pws*/) {


            tCommerceDataSource.getListStockNew(/*usr , pws*/).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<StockListModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(StockListModel productList) {
                            HomeFragment.dialog.dismiss();
                            view.showNewProduct(productList);
                            view.showTopSellProduct(productList);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showMessage(e.toString());

                        }
                    });



    }

    @Override
    public void getTopSellProduct() {

    }

    @Override
    public void getHitsProduct() {

            tCommerceDataSource.getListStockBestView().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<StockListModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(StockListModel stockListModel) {
                            HomeFragment.dialog.dismiss();
                            view.showHitsProduct(stockListModel);

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showMessage(e.toString());

                        }
                    });


    }

    @Override
    public void getSlider() {

            tCommerceDataSource.getSlideImage().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ItemImageSlider>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(ItemImageSlider itemImageSlider) {
                            HomeFragment.dialog.dismiss();
                            view.showSlider(itemImageSlider);

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showMessage(e.toString());

                        }
                    });


    }
}
