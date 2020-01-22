package com.intechdev.tcommerce.Blog_Post;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.BlogPost;

public interface BlogPostContract {

    interface View extends BaseView{
        void getBlogPost(BlogPost blogPost);
    }

    interface Presenter extends BasePresenter<View>{
        void getBlogPost(int usrid,int id);
    }
}
