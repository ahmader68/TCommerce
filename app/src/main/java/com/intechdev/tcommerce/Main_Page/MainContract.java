package com.intechdev.tcommerce.Main_Page;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.InitProg;

public interface MainContract {

    interface View extends BaseView{
        void getInit(InitProg initProg);
    }

    interface Presenter extends BasePresenter<View>{
        void getInit();
    }
}
