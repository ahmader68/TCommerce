package com.intechdev.tcommerce.TransActions;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.TransactionsAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemTransactions;
import com.intechdev.tcommerce.R;

public class TransActionsFragment extends BaseFragment implements TransActionsContract.View {

    private TransActionsContract.Presenter presenter;

    private RecyclerView rvTransactionsList;
    private TransactionsAdapter transactionsAdapter;

    private int userId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TransActionsPresenter(new TCommerceRepository());
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId",0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_transactions;
    }

    @Override
    public void setupViews() {
        rvTransactionsList = rootView.findViewById(R.id.rv_transactionsFragment_transactionsList);
        presenter.getTransactions(userId);

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
    public void showTransactions(ItemTransactions itemTransactions) {
        if(itemTransactions != null) {
            transactionsAdapter = new TransactionsAdapter(getViewContext(), itemTransactions);
            rvTransactionsList.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
            rvTransactionsList.setAdapter(transactionsAdapter);
        }
    }
}
