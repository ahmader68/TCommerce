package com.intechdev.tcommerce.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Change_Pass.ChangePassFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Discounts.DiscountsFragment;
import com.intechdev.tcommerce.Downloads.DownloadsFragment;
import com.intechdev.tcommerce.Favourites.FavouritesFragment;
import com.intechdev.tcommerce.History_Orders.HistoryFragment;
import com.intechdev.tcommerce.Home.HomeFragment;
import com.intechdev.tcommerce.Message.MessageFragment;
import com.intechdev.tcommerce.My_Addresses.MyAddressFragment;
import com.intechdev.tcommerce.My_Profile.MyProfileFragment;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Support.SupportFragment;
import com.intechdev.tcommerce.TransActions.TransActionsFragment;

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    private ProfileContract.Presenter presenter;

    private TextView txtFullName;

    private SharedPreferences sharedPreferences;

    private LinearLayout
            linearMyProfile,
            linearChangePass,
            linearMyAddress,
            linearOrderHistory,
            linearDownloads,
            linearFavourites,
            linearTransactions,
            linearMyDiscounts,
            linearMessages,
            linearContact,
            linearExit;

    private String fullName,mobile,email,username;

    private int userId,userLevel;

    private Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProfilePresenter(new TCommerceRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void setupViews() {

        linearChangePass = rootView.findViewById(R.id.linear_fragmentProfile_linearChangePass);
        linearContact = rootView.findViewById(R.id.linear_fragmentProfile_linearContact);
        linearDownloads = rootView.findViewById(R.id.linear_fragmentProfile_linearDownloads);
        linearExit = rootView.findViewById(R.id.linear_fragmentProfile_linearExit);
        linearFavourites = rootView.findViewById(R.id.linear_fragmentProfile_linearFavourite);
        linearMessages = rootView.findViewById(R.id.linear_fragmentProfile_linearMessages);
        linearMyAddress = rootView.findViewById(R.id.linear_fragmentProfile_linearMyAddress);
        linearMyDiscounts = rootView.findViewById(R.id.linear_fragmentProfile_linearMyDiscounts);
        linearMyProfile = rootView.findViewById(R.id.linear_fragmentProfile_linearProfile);
        linearOrderHistory = rootView.findViewById(R.id.linear_fragmentProfile_linearHistoryOrder);
        linearTransactions = rootView.findViewById(R.id.linear_fragmentProfile_linearTransactions);

        txtFullName = rootView.findViewById(R.id.txt_fragmentProfile_fullName);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());

        fullName = sharedPreferences.getString("fullname",null);
        mobile = sharedPreferences.getString("mobile",null);
        email = sharedPreferences.getString("email",null);
        userId = sharedPreferences.getInt("userId",0);
        userLevel = sharedPreferences.getInt("userLevel",0);
        username = sharedPreferences.getString("username",null);
        txtFullName.setText(fullName);

        linearMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name : editProfile(email,mobile,userId)   It has a problem: this Web service should get the username too

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                bundle.putString("mobile",mobile);
                bundle.putString("email",email);
                bundle.putString("username",username);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MyProfileFragment profileFragment = new MyProfileFragment();
                profileFragment.setArguments(bundle);
                transaction.replace(R.id.frm_mainFragment_mainLayout,profileFragment);
                transaction.commit();
            }
        });

        linearChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name : changePass(userId,pwd)    It has a problem: this Web service should get the new password too

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ChangePassFragment changePassFragment = new ChangePassFragment();
                changePassFragment.setArguments(bundle);
                transaction.replace(R.id.frm_mainFragment_mainLayout,changePassFragment);
                transaction.commit();
            }
        });

        linearMyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name : getListAddress(userId)

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MyAddressFragment myAddressFragment = new MyAddressFragment();
                myAddressFragment.setArguments(bundle);
                transaction.replace(R.id.frm_mainFragment_mainLayout,myAddressFragment);
                transaction.commit();
            }
        });

        linearOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Apiservice name : getOrders(userId)

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                HistoryFragment historyFragment = new HistoryFragment();
                historyFragment.setArguments(bundle);
                transaction.replace(R.id.frm_mainFragment_mainLayout,historyFragment);
                transaction.commit();
            }
        });

        linearDownloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Downloads does not have api service

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                DownloadsFragment downloadsFragment = new DownloadsFragment();
                downloadsFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,downloadsFragment);
                transaction.commit();
            }
        });

        linearFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name: getFavorites(userId)

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                FavouritesFragment favouritesFragment = new FavouritesFragment();
                favouritesFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,favouritesFragment);
                transaction.commit();
            }
        });

        linearTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name: getTransactions(userId)

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                TransActionsFragment transActionsFragment = new TransActionsFragment();
                transActionsFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,transActionsFragment);
                transaction.commit();
            }
        });

        linearMyDiscounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name : getDiscount(userId)

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                DiscountsFragment discountsFragment = new DiscountsFragment();
                discountsFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,discountsFragment);
                transaction.commit();
            }
        });

        linearMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api service name : getListComment(userId)

                bundle = new Bundle();
                bundle.putInt("userId",userId);
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,messageFragment);
                transaction.commit();
            }
        });

        linearContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupportFragment supportFragment = new SupportFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,supportFragment);
                transaction.commit();
            }
        });

        linearExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,homeFragment);
                transaction.commit();
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
}
