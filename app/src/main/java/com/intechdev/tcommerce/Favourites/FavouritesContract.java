package com.intechdev.tcommerce.Favourites;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.Favourite;
import com.intechdev.tcommerce.Models.Result;

public interface FavouritesContract {

    interface View extends BaseView{
        void getFavourite(Favourite favourite);
        void deleteFav(int userId,int itemId);
        void deleteSuccess(Result result);
    }

    interface Presenter extends BasePresenter<View>{

        void getFavourite(int userid);
        void deleteFav(int userId,int itemId);

    }
}
