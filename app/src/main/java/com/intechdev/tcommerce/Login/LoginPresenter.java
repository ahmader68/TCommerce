package com.intechdev.tcommerce.Login;

import android.widget.Toast;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.Login;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private TCommerceDataSource tCommerceDataSource;
    private LoginContract.View view;
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(TCommerceDataSource tCommerceDataSource) {
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void login(Login login) {
        tCommerceDataSource.login(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Login login) {
                        int id = login.getResult();
                        if(id == -2 || id == -1){
                            switch (id){
                                case -1:
                                    view.showMessage("کاربری با این اطلاعات وجود ندارد");
                                    break;
                                case -2:
                                    view.showMessage("کاربر غیرفعال می باشد");
                                    break;
                            }
                        }else{
                            view.loginSuccess(login);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
