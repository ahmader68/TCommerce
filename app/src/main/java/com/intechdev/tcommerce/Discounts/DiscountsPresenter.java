package com.intechdev.tcommerce.Discounts;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.ItemDiscounts;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DiscountsPresenter implements DiscountsContract.Presenter {

    private DiscountsContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable;

    DiscountsPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        this.compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(DiscountsContract.View view) {
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
    public void getDiscounts(int userId) {
        tCommerceDataSource.getDiscounts(userId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ItemDiscounts>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ItemDiscounts itemDiscounts) {
                        if(itemDiscounts == null){
                            view.showMessage("تاکنون تخفیفی برای شما ثبت نشده است");
                        }else{
                            view.showDiscounts(itemDiscounts);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
