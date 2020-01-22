package com.intechdev.tcommerce.Stock_Details;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemStockDetails;
import com.intechdev.tcommerce.Models.Result;

public interface StockDetailsContract {
    interface View extends BaseView {
        void showStockDetails(ItemStockDetails.ItemStockDetailsObject itemStockDetailsObject);
        void addToBasketSuccess(Result result);
        void addToFav(Result result);
        void deleteFav(Result result);
    }

    interface Presenter extends BasePresenter<View> {
        void getItemStockDetails(long id);
        void addToBasket(int ProdId, int OrderCount,int usrid);
        void addToFav(int userid,int itemId);
        void deleteFav(int userid,int itemId);
    }
}
