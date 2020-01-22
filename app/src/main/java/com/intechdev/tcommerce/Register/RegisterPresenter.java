package com.intechdev.tcommerce.Register;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.Login;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private CompositeDisposable compositeDisposable;
    private TCommerceDataSource tCommerceDataSource;

    public RegisterPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(RegisterContract.View view) {
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
    public void register(Login login) {
        tCommerceDataSource.register(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Login login) {
                        int id = login.getResult();
                        if(id < 0){
                            switch (id){
                                case -1:
                                    view.showMessage("اطلاعات را کامل وارد کنید");
                                    break;
                                case -2:
                                    view.showMessage("کلمه های عبور باهم برابر نیستند");
                                    break;
                                case -4:
                                    view.showMessage("کاربر با این مشخصات قبلا ثبت شده است");
                                    break;
                            }
                        }else{
                            view.registerSuccess(login);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
