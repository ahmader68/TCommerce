package com.intechdev.tcommerce.History_Orders;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.OrderHistoryAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemOrders;
import com.intechdev.tcommerce.R;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {
    private HistoryContract.Presenter presenter;

    private RecyclerView rvHistoryOrder;
    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HistoryPresenter(new TCommerceRepository());
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId", 0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_history;
    }

    @Override
    public void setupViews() {
        rvHistoryOrder = rootView.findViewById(R.id.rv_historyFragment_historyList);
        presenter.getOrderHistory(userId);
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
    public void showOrderHistory(ItemOrders itemOrders) {
        if (itemOrders != null) {
            OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(getViewContext(), itemOrders);
            rvHistoryOrder.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
            rvHistoryOrder.setAdapter(orderHistoryAdapter);
        }
    }
}
