package com.intechdev.tcommerce.Discounts;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.DiscountsAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemDiscounts;
import com.intechdev.tcommerce.R;

public class DiscountsFragment extends BaseFragment implements DiscountsContract.View {

    private DiscountsContract.Presenter presenter;

    private RecyclerView rvDiscounts;

    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DiscountsPresenter(new TCommerceRepository());
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId",0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_discounts;
    }

    @Override
    public void setupViews() {
        rvDiscounts = rootView.findViewById(R.id.rv_discountsFragment_discount);
        presenter.getDiscounts(userId);
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
    public void showDiscounts(ItemDiscounts itemDiscounts) {
        DiscountsAdapter discountsAdapter = new DiscountsAdapter(itemDiscounts,getViewContext());
        rvDiscounts.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvDiscounts.setAdapter(discountsAdapter);
    }
}
