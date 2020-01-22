package com.intechdev.tcommerce.Basket;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.ItemOrdersBasket;
import com.intechdev.tcommerce.Models.Result;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BasketPresenter implements BasketContract.Presenter {

    private CompositeDisposable compositeDisposable;
    private TCommerceDataSource tCommerceDataSource;
    private BasketContract.View view;

    public BasketPresenter(TCommerceDataSource tCommerceDataSource) {
        this.tCommerceDataSource = tCommerceDataSource;

        compositeDisposable = new CompositeDisposable();
    }



    @Override
    public void attachView(BasketContract.View view) {
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
    public void deleteFromBasket(int usrid, int OrderId) {
        tCommerceDataSource.deleteFromBasket(usrid, OrderId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Result result) {
                        if(result.getResult() == -1){
                            view.showMessage("لطفا مجدد تلاش کنید");
                        }else {
                            view.deleteFromBasketSuccessful(result);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }

    @Override
    public void getOrdersInBasket(int usrid) {
        tCommerceDataSource.showOrderInBasket(usrid).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ItemOrdersBasket>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ItemOrdersBasket itemOrdersBasket) {
                        view.showOrdersInBasket(itemOrdersBasket);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
