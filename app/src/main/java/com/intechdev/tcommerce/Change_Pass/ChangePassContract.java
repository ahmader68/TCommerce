package com.intechdev.tcommerce.Change_Pass;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.Login;

public interface ChangePassContract {
    interface View extends BaseView{
        void changePassSuccess(Login login);
    }

    interface Presenter extends BasePresenter<View>{
        void changePassword(Login login);
    }
}
