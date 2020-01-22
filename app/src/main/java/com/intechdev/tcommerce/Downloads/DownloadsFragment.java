package com.intechdev.tcommerce.Downloads;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.R;

public class DownloadsFragment extends BaseFragment implements DownloadsContract.View {
    private DownloadsContract.Presenter presenter;

    private RecyclerView rvDownloads;

    int userId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DownloadsPresenter(new TCommerceRepository());
        Bundle bundle = getArguments();
        userId = bundle.getInt("userId",0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_downloads;
    }

    @Override
    public void setupViews() {
        rvDownloads = rootView.findViewById(R.id.rv_downloadsFragment_downloadList);
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
}
