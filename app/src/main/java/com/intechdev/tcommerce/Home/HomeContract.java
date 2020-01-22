package com.intechdev.tcommerce.Home;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Classes.Alert_Dialog;
import com.intechdev.tcommerce.Models.ItemImageSlider;
import com.intechdev.tcommerce.Models.StockListModel;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void showNewProduct(StockListModel list);
        void showTopSellProduct(StockListModel list);
        void showHitsProduct(StockListModel list);
        void showSlider(ItemImageSlider itemImageSlider);
    }
    interface Presenter extends BasePresenter<View> {
        void getNewProduct(/*String usr , String pws*/);
        void getTopSellProduct();
        void getHitsProduct();
        void getSlider();
    }
}
