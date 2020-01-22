package com.intechdev.tcommerce.Change_Pass;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.Login;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePassPresenter implements ChangePassContract.Presenter {

    private TCommerceDataSource tCommerceDataSource;
    private ChangePassContract.View view;
    private CompositeDisposable compositeDisposable;

    ChangePassPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(ChangePassContract.View view) {
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
    public void changePassword(Login login) {
        tCommerceDataSource.changePassword(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Login login) {
                        int id = login.getResult();
                        if(id < 0 ){
                            switch (id){
                                case -1:
                                    view.showMessage("پر کردن تمامی فیلدها اجباری است");
                                    break;
                                case -2:
                                    view.showMessage("پسورد جدید به درستی وارد شنده است");
                                    break;
                            }
                        }else{
                            view.changePassSuccess(login);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
