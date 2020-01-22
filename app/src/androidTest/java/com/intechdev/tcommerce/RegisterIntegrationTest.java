package com.intechdev.tcommerce;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.Register.RegisterContract;
import com.intechdev.tcommerce.Register.RegisterPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegisterIntegrationTest {

    private RegisterContract.Presenter presenter;
    private Login login;

    @Before
    public void setUp(){
        presenter = new RegisterPresenter(new TCommerceRepository());
        login = new Login();
    }

    @Test
    public void WhenAllParametersFillCorrectly(){
        login.setFirstName("hooman");
        login.setLastName("hooshyar");
        login.setUsr("hhooshyar");
        login.setEmail("h@h.com");
        login.setMobile("09333763513");
        login.setPwd("123");
        presenter.register(login);
    }
}
