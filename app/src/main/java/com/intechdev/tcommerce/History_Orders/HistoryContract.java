package com.intechdev.tcommerce.History_Orders;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemOrders;

public interface HistoryContract {

    interface View extends BaseView{
        void showOrderHistory(ItemOrders itemOrders);
    }

    interface Presenter extends BasePresenter<View>{
        void getOrderHistory(int userId);
    }
}
