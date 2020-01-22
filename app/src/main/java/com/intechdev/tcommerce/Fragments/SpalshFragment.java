package com.intechdev.tcommerce.Fragments;

import android.content.Context;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Main_Page.MainFragment;
import com.intechdev.tcommerce.R;

public class SpalshFragment extends BaseFragment {

    private ImageView imgLogo;
    private final int DURATION = 2000;
    private Animation animation;
    @Override
    public int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void setupViews() {
        animation = AnimationUtils.loadAnimation(getContext(),R.anim.fadein);
        imgLogo = rootView.findViewById(R.id.img_fragmentSplash_logo);
        imgLogo.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment fragment = new MainFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainActivity_mainLayout,fragment);
                transaction.commitAllowingStateLoss();
            }
        },DURATION);
    }


}
