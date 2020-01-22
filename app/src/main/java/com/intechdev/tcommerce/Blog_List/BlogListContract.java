package com.intechdev.tcommerce.Blog_List;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemBlog;

public interface BlogListContract {
    interface View extends BaseView{
        void getBlogList(ItemBlog itemBlog);
    }

    interface Presenter extends BasePresenter<View>{
        void getBlogList(int usrid);
    }
}
