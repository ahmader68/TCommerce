package com.intechdev.tcommerce.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.os.Bundle;

import com.intechdev.tcommerce.Fragments.SpalshFragment;
import com.intechdev.tcommerce.Main_Page.MainFragment;
import com.intechdev.tcommerce.R;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentTransaction transaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new SpalshFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_mainActivity_mainLayout, fragment);
        transaction.commit();

    }
}
