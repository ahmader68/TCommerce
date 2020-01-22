package com.intechdev.tcommerce.Discounts;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemDiscounts;

public interface DiscountsContract {

    interface View extends BaseView{
        void showDiscounts(ItemDiscounts itemDiscounts);
    }

    interface Presenter extends BasePresenter<View>{
        void getDiscounts(int userId);
    }
}
