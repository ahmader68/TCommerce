package com.intechdev.tcommerce.Blog_Post;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.BlogPost;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BlogPostPresenter implements BlogPostContract.Presenter {

    private BlogPostContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable;

    BlogPostPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(BlogPostContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getBlogPost(int usrid, int id) {
        tCommerceDataSource.getBlogPost(usrid, id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BlogPost>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(BlogPost blogPost) {
                        view.getBlogPost(blogPost);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
