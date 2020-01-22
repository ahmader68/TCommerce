package com.intechdev.tcommerce.My_Profile;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.Login;

public interface MyProfileContract {
    interface View extends BaseView {
        void showSuccessEdit(Login login);
    }

    interface Presenter extends BasePresenter<View> {
        void changeProfile(Login login);
    }
}
