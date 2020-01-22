package com.intechdev.tcommerce.TransActions;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemTransactions;

public interface TransActionsContract {
    interface View extends BaseView {
        void showTransactions(ItemTransactions itemTransactions);
    }

    interface Presenter extends BasePresenter<View> {
        void getTransactions(int userId);
    }
}
