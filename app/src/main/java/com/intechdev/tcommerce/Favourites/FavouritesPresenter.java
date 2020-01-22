package com.intechdev.tcommerce.Favourites;

import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.Favourite;
import com.intechdev.tcommerce.Models.Result;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavouritesPresenter implements FavouritesContract.Presenter {

    private FavouritesContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable;
    FavouritesPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(FavouritesContract.View view) {
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
    public void getFavourite(int userid) {
        tCommerceDataSource.getFavourite(userid).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Favourite>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Favourite favourite) {
                        view.getFavourite(favourite);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }

    @Override
    public void deleteFav(int userId, int itemId) {
        tCommerceDataSource.deleteFav(userId, itemId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Result result) {
                        view.deleteSuccess(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
