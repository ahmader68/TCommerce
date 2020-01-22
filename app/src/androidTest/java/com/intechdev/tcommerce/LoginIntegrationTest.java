package com.intechdev.tcommerce;

import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Login.LoginContract;
import com.intechdev.tcommerce.Login.LoginPresenter;
import com.intechdev.tcommerce.Models.Login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginIntegrationTest {
    private LoginContract.Presenter presenter;
    private Login login;

    @Before
    public void setUp(){
        presenter = new LoginPresenter(new TCommerceRepository());
        login = new Login();
    }

    @Test
    public void LoginUserWithFakeUsername(){
        login.setUsr("aaa");
        login.setPwd("111");
        presenter.login(login);
    }

    @Test
    public void LoginUserWithValidUsername(){
        login.setUsr("hhooshyar");
        login.setPwd("123456");
        presenter.login(login);
    }
}
