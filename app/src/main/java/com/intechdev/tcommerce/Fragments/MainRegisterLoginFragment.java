package com.intechdev.tcommerce.Fragments;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Login.LoginFragment;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Register.RegisterFragment;

public class MainRegisterLoginFragment extends BaseFragment {

    private RadioGroup rgMain;
    private RadioButton rbLogin;
    private RadioButton rbRegister;
    private FragmentTransaction transaction;

    private Bundle bundle;
    private int flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        flag = bundle.getInt("flag",-1);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_login_register;
    }

    @Override
    public void setupViews() {
        rgMain = rootView.findViewById(R.id.rg_mainLoginFragment_radioGroup);
        rbLogin = rootView.findViewById(R.id.rb_mainLoginFragment_login);
        rbRegister = rootView.findViewById(R.id.rb_mainLoginFragment_register);
        if(flag == -1 || flag == 1) {
            rbLogin.setChecked(true);
            rbRegister.setChecked(false);
            transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
            transaction.replace(R.id.frm_mainLoginFragment_layout, new LoginFragment());
            transaction.commit();
        }else if(flag == 0){
            rbRegister.setChecked(true);
            rbLogin.setChecked(false);
            transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
            transaction.replace(R.id.frm_mainLoginFragment_layout,new RegisterFragment());
            transaction.commit();
        }
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_mainLoginFragment_login){
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
                    transaction.replace(R.id.frm_mainLoginFragment_layout,new LoginFragment());
                    transaction.commit();
                }
                if(checkedId == R.id.rb_mainLoginFragment_register){
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                    transaction.replace(R.id.frm_mainLoginFragment_layout,new RegisterFragment());
                    transaction.commit();
                }
            }
        });
    }
}
