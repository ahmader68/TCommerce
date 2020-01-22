package com.intechdev.tcommerce.Group_Stock;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemGroupStock;

import java.util.List;

public interface GroupStockContract {

    interface View extends BaseView {
        void setGroup(List<ItemGroupStock.Items> items);
    }

    interface Presenter extends BasePresenter<View> {
        void getGroup();
    }
}
