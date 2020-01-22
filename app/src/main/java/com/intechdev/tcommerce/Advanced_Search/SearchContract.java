package com.intechdev.tcommerce.Advanced_Search;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;

import java.util.List;

import io.reactivex.Single;

public interface SearchContract {
    interface View extends BaseView{
        void showMainSearch(List<ItemAdvanceSearch.ItemMain> itemAdvanceSearch);

        void showSubMainSearch(List<ItemAdvanceSearch.ItemMain>  mainItems,List<ItemAdvanceSearch.ItemMain> item , String type , int pos);

        void showList(List<ItemAdvanceSearch.ItemMain> items);
    }
    interface Presenter extends BasePresenter<View>{
        void getAdvanceSearchItem(int prodId);
    }
}
