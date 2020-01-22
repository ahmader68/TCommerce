package com.intechdev.tcommerce.My_Profile;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.Login;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyProfilePresenter implements MyProfileContract.Presenter {

    private MyProfileContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    MyProfilePresenter(TCommerceDataSource tCommerceDataSource) {
        this.tCommerceDataSource = tCommerceDataSource;
    }

    @Override
    public void attachView(MyProfileContract.View view) {
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
    public void changeProfile(Login login) {
        tCommerceDataSource.editUser(login).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Login login) {
                        int result = login.getResult();
                        if (result < 0) {
                            switch (result) {
                                case -1:
                                    view.showMessage("لطفا شماره موبایل را وارد کنید");
                                    break;
                                case -2:
                                    view.showMessage("مشکلی در به روز رسانی بوجود آمده است. لطفا مجدد تلاش کنید");
                                    break;
                                case -5:
                                    view.showMessage("این نام کاربری برای شما مجاز نمی باشد");
                                    break;
                            }
                        } else {
                            view.showSuccessEdit(login);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
