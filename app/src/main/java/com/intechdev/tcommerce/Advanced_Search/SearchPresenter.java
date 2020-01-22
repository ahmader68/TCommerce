package com.intechdev.tcommerce.Advanced_Search;

import com.intechdev.tcommerce.Adapters.AdvanceSearchSubMainAdapter;
import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private TCommerceDataSource tCommerceDataSource;
    private CompositeDisposable compositeDisposable;
    private AdvanceSearchSubMainAdapter adapter;

    SearchPresenter(TCommerceDataSource tCommerceDataSource){
        this.tCommerceDataSource = tCommerceDataSource;
        this.compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(SearchContract.View view) {
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
    public void getAdvanceSearchItem(int prodId) {
        tCommerceDataSource.getAdvanceSearchItem(prodId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ItemAdvanceSearch>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ItemAdvanceSearch itemAdvanceSearch) {
                        view.showMainSearch(itemAdvanceSearch.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
