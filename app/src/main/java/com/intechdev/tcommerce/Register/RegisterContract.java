package com.intechdev.tcommerce.Register;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.Login;

public interface RegisterContract {
    interface View extends BaseView{
        void registerSuccess(Login login);
    }

    interface Presenter extends BasePresenter<View>{
        void register(Login login);
    }
}
