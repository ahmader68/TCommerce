package com.intechdev.tcommerce.Basket;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemOrdersBasket;
import com.intechdev.tcommerce.Models.Result;

public interface BasketContract {

    interface View extends BaseView{
        void deleteFromBasketSuccessful(Result result);
        void deleteFromBasket(int usrid,int OrderId, int position,ItemOrdersBasket itemOrdersBasket);
        void showOrdersInBasket(ItemOrdersBasket itemOrdersBasket);
    }
    interface Presenter extends BasePresenter<View>{
        void deleteFromBasket(int usrid,int OrderId);
        void getOrdersInBasket(int usrid);
    }
}
