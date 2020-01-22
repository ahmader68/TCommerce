package com.intechdev.tcommerce.Welcome;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;

public class WelcomeFragment extends BaseFragment implements WelcomeContract.View {

    private WelcomeContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WelcomePresenter(new TCommerceRepository());
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void setupViews() {

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
