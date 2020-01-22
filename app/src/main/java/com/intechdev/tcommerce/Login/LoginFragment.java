package com.intechdev.tcommerce.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Register.RegisterFragment;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    private LoginContract.Presenter presenter;

    private TextInputLayout tilFragmentLoginUsername,tilFragmentLoginPassword;

    private TextInputEditText edtFragmentLoginUsername,edtFragmentLoginPassword;

    private Button btnLogin;

    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(new TCommerceRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setupViews() {
        tilFragmentLoginUsername = rootView.findViewById(R.id.til_loginFragment_username);
        tilFragmentLoginPassword = rootView.findViewById(R.id.til_loginFragment_password);

        edtFragmentLoginUsername = rootView.findViewById(R.id.edt_loginFragment_user);
        edtFragmentLoginPassword = rootView.findViewById(R.id.edt_loginFragment_password);

        btnLogin = rootView.findViewById(R.id.btn_loginFragment_login);

        InputMethodManager imm = (InputMethodManager) getViewContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtFragmentLoginUsername.getText().toString();
                String pass = edtFragmentLoginPassword.getText().toString();
                if(username.equals("") || pass.equals("")){
                    Toast.makeText(getViewContext(), "لطفا نام کاربری و کلمه عبور را وارد کنید", Toast.LENGTH_SHORT).show();
                }else{
                    Login login = new Login();
                    login.setUsr(username);
                    login.setPwd(pass);
                    presenter.login(login);
                }
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
    public void loginSuccess(Login login) {
        String mobile = login.getMobile();
        String fullName = login.getFirstName() + " " + login.getLastName();
        int userId = login.getResult();
        int userLevel = login.getUserLevel();
        String email = login.getEmail();
        String username = login.getUsr();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullname",fullName);
        editor.putString("mobile",mobile);
        editor.putInt("userId",userId);
        editor.putInt("userLevel",userLevel);
        editor.putString("email",email);
        editor.putString("username",username);

        editor.apply();
        editor.commit();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_mainLoginFragment_layout,new RegisterFragment());
        transaction.commit();
        Toast.makeText(getViewContext(), login.getUsr() + "به تیکامرس خوش آمدید", Toast.LENGTH_SHORT).show();
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
