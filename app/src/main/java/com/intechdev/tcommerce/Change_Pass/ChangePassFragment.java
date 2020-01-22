package com.intechdev.tcommerce.Change_Pass;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.R;

public class ChangePassFragment extends BaseFragment implements ChangePassContract.View {
    private ChangePassContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChangePassPresenter(new TCommerceRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_change_pass;
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

    @Override
    public void changePassSuccess(Login login) {
        int id = login.getResult();
        if(id > 0){
            showMessage("کلمه عبور با موفقیت تغییر کرد");
        }
    }
}
