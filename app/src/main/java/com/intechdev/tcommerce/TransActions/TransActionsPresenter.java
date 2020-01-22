package com.intechdev.tcommerce.TransActions;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.ItemTransactions;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransActionsPresenter implements TransActionsContract.Presenter {

    private TransActionsContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    TransActionsPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
    }

    @Override
    public void attachView(TransActionsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void getTransactions(int userId) {
        tCommerceDataSource.getTransactions(userId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ItemTransactions>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ItemTransactions itemTransactions) {
                        if(itemTransactions == null){
                            view.showMessage("شما هیچ تراکنشی تا کنون انجام نداده اید");
                        }else {
                            view.showTransactions(itemTransactions);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
