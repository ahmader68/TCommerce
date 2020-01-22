package com.intechdev.tcommerce.My_Addresses;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.ItemAddress;
import com.intechdev.tcommerce.Models.Result;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyAddressPresenter implements MyAddressContract.Presenter {

    private TCommerceDataSource tCommerceDataSource;
    private MyAddressContract.View view;
    private CompositeDisposable compositeDisposable;

    MyAddressPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(MyAddressContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void addAddress(ItemAddress address) {
        tCommerceDataSource.addAddress(address).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Result result) {
                        if(result.getResult() < -1){
                           view.showMessage("پر کردن همه فیلدها اجباری است");
                        }else{
                            view.successAddAddress(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getAddress(int userId) {
        tCommerceDataSource.getAddresses(userId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ItemAddress>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ItemAddress address) {
                        view.getAddresses(address);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }

    @Override
    public void deleteAddress(int userId, int addressId) {
        tCommerceDataSource.deleteAddress(userId, addressId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Result result) {
                        view.successDeleteAddress(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }



    @Override
    public void editAddress(ItemAddress address) {
        tCommerceDataSource.editAddress(address).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Result result) {
                        if(result.getResult() < 0){
                            view.showMessage("خطا دارد");
                        }else{
                            view.successEditAddress(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
