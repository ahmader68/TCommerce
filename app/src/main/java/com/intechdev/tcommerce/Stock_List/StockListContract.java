package com.intechdev.tcommerce.Stock_List;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemListStock;
import com.intechdev.tcommerce.Models.StockListModel;

import java.util.Map;

public interface StockListContract {
    interface View extends BaseView {
        void showNewProduct(StockListModel list);
        void showListStockSort(ItemListStock list);
        void showListStock(ItemListStock list);
        void setPageStatrtSearchData();
    }

    interface Presenter extends BasePresenter<View> {
        void getNewProducts();
        void getHitsProduct();
        void getBestSellsProduct();

        void getListStock(String categoryId, String subCat, String sort);
        void getListStock(Map<String, String> data);
        void getListStock(String categoryId, String sort);
    }
}
