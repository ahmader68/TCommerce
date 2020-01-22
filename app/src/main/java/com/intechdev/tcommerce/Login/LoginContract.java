package com.intechdev.tcommerce.Login;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.Login;

public interface LoginContract {

    interface View extends BaseView{
        void loginSuccess(Login login);
    }

    interface Presenter extends BasePresenter<View>{
        void login(Login login);
    }
}
