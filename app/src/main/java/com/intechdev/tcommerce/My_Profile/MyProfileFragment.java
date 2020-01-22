package com.intechdev.tcommerce.My_Profile;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Data.TCommerceDataSource;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.R;

public class MyProfileFragment extends BaseFragment implements MyProfileContract.View {

    private TextInputLayout tilEmail,tilMobile,tilUsername;

    private TextInputEditText edtEmail,edtMobile,edtUsername;

    private Button btnChange;

    private MyProfileContract.Presenter presenter;

    private Bundle bundle;

    private int userId;

    private String username,mobile,email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyProfilePresenter(new TCommerceRepository());
        bundle = getArguments();
        userId = bundle.getInt("userId",0);
        username = bundle.getString("username",null);
        mobile = bundle.getString("mobile",null);
        email = bundle.getString("email",null);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_profile;
    }

    @Override
    public void setupViews() {

        tilEmail = rootView.findViewById(R.id.til_myProfileFragment_email);
        tilMobile = rootView.findViewById(R.id.til_myProfileFragment_mobile);
        tilUsername = rootView.findViewById(R.id.til_myProfileFragment_name);

        edtMobile = rootView.findViewById(R.id.edt_myProfileFragment_mobile);
        edtEmail = rootView.findViewById(R.id.edt_myProfileFragment_email);
        edtUsername = rootView.findViewById(R.id.edt_myProfileFragment_name);

        btnChange = rootView.findViewById(R.id.btn_myProfileFragment_accept);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();
                login.setUsrId(userId);
                login.setUsr(username);
                login.setEmail(email);
                login.setMobile(mobile);
                presenter.changeProfile(login);
            }
        });




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
    public void showSuccessEdit(Login login) {
        int id = login.getResult();
        if(id > 0){
            showMessage("به روز رسانی با موفقیت انجام شد");
        }
    }
}
