package com.intechdev.tcommerce.Register;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.R;

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    private RegisterContract.Presenter presenter;

    private TextInputLayout
            tilFirstname,
            tilLastname,
            tilUsername,
            tilEmail,
            tilMobile,
            tilPassword,
            tilConPassword;

    private TextInputEditText
            edtFirstname,
            edtLastname,
            edtUsername,
            edtEmail,
            edtMobile,
            edtPassword,
            edtConPassword;

    private Button btnRegister;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresenter(new TCommerceRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void setupViews() {

        tilFirstname = rootView.findViewById(R.id.til_registerFragment_name);
        tilLastname = rootView.findViewById(R.id.til_registerFragment_family);
        tilUsername = rootView.findViewById(R.id.til_registerFragment_username);
        tilEmail = rootView.findViewById(R.id.til_registerFragment_email);
        tilMobile = rootView.findViewById(R.id.til_registerFragment_mobile);
        tilPassword = rootView.findViewById(R.id.til_registerFragment_password);
        tilConPassword = rootView.findViewById(R.id.til_registerFragment_repeatPass);

        edtFirstname = rootView.findViewById(R.id.edt_registerFragment_name);
        edtLastname = rootView.findViewById(R.id.edt_registerFragment_family);
        edtUsername = rootView.findViewById(R.id.edt_registerFragment_username);
        edtEmail = rootView.findViewById(R.id.edt_registerFragment_email);
        edtMobile = rootView.findViewById(R.id.edt_registerFragment_mobile);
        edtPassword = rootView.findViewById(R.id.edt_registerFragment_password);
        edtConPassword = rootView.findViewById(R.id.edt_registerFragment_repeatPass);

        btnRegister = rootView.findViewById(R.id.btn_registerFragment_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login login = new Login();
                String firstname = edtFirstname.getText().toString();
                String lastname = edtLastname.getText().toString();
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String mobile = edtMobile.getText().toString();
                String password = edtPassword.getText().toString();
                String conPassword = edtConPassword.getText().toString();

                if(!validateName() || !validateLastname() || !validateEmail() || !validateMobile() || !validatePassword() || !validateConPass() || !validateUsername()){
                    return;
                }else if(!password.equals(conPassword)){
                    showMessage("کلمه عبور و تکرار کلمه عبور باهم برابر نیستند");
                }else{
                    login.setFirstName(firstname);
                    login.setLastName(lastname);
                    login.setEmail(email);
                    login.setUsr(username);
                    login.setMobile(mobile);
                    login.setPwd(password);
                    presenter.register(login);
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
    public void registerSuccess(Login login) {
        Toast.makeText(getViewContext(), "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
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

    private boolean validateName(){
        String name = edtFirstname.getText().toString().trim();
        if(name.isEmpty()){
            tilFirstname.setError("وارد کردن نام اجباری است");
            tilFirstname.requestFocus();
            return false;
        }else{
            tilFirstname.setError(null);
            return true;
        }
    }

    private boolean validateLastname(){
        String lastname = edtLastname.getText().toString().trim();
        if(lastname.isEmpty()){
            tilLastname.setError("وارد کردن نام خانوادگی اجباری است");
            tilLastname.requestFocus();
            return false;
        }else{
            tilLastname.setError(null);
            return true;
        }
    }

    private boolean validateUsername(){
        String username = edtUsername.getText().toString().trim();
        if(username.isEmpty()){
            tilUsername.setError("وارد کردن نام کاربری اجباری است");
            tilUsername.requestFocus();
            return false;
        }else{
            tilUsername.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String email = edtEmail.getText().toString().trim();
        if(email.isEmpty()){
            tilEmail.setError("وارد کردن ایمیل اجباری است");
            tilEmail.requestFocus();
            return false;
        }else{
            tilEmail.setError(null);
            return true;
        }
    }

    private boolean validateMobile(){
        String mobile = edtMobile.getText().toString().trim();
        if(mobile.isEmpty()){
            tilMobile.setError("وارد کردن موبایل اجباری است");
            tilMobile.requestFocus();
            return false;
        }else{
            tilMobile.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){

        String password = edtPassword.getText().toString().trim();
        if(password.isEmpty()){
            tilPassword.setError("وارد کردن کلمه عبور اجباری است");
            tilPassword.requestFocus();
            return false;
        }else{
            tilPassword.setError(null);
            return true;
        }
    }

    private boolean validateConPass(){
        String conPassword = edtConPassword.getText().toString().trim();
        if(conPassword.isEmpty()){
            tilConPassword.setError("وارد کردن تکرار کلمه عبور اجباری است");
            tilConPassword.requestFocus();
            return false;
        }else{
            tilConPassword.setError(null);
            return true;
        }
    }
}
