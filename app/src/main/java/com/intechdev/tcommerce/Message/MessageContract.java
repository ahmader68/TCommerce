package com.intechdev.tcommerce.Message;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;

public interface MessageContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{

    }
}
