package com.intechdev.tcommerce.Favourites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.FavouriteAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.Favourite;
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.R;

public class FavouritesFragment extends BaseFragment implements FavouritesContract.View {

    private FavouritesContract.Presenter presenter;
    private FavouriteAdapter favouriteAdapter;

    private RecyclerView rvList;

    private SharedPreferences sharedPreferences;

    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FavouritesPresenter(new TCommerceRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userId",0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_favourites;
    }

    @Override
    public void setupViews() {

        rvList = rootView.findViewById(R.id.rv_favouritesFragment_favouritesList);
        presenter.getFavourite(userId);

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void getFavourite(Favourite favourite) {
        favouriteAdapter = new FavouriteAdapter(getViewContext(),favourite,this,userId);
        rvList.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvList.setAdapter(favouriteAdapter);
    }

    @Override
    public void deleteFav(int userId, int itemId) {
        presenter.deleteFav(userId, itemId);
    }

    @Override
    public void deleteSuccess(Result result) {
        if(result.getResult() > 0){
            showMessage("محصول با موفقیت از لیست علاقمندی ها حذف گردید");
            presenter.getFavourite(userId);
        }

    }
}
