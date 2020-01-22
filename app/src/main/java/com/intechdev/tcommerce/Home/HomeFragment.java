package com.intechdev.tcommerce.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.sliderviewlibrary.SliderView;
import com.intechdev.tcommerce.Adapters.HomeRecyclerAdapter;
import com.intechdev.tcommerce.Advanced_Search.SearchFragment;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Classes.Alert_Dialog;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemImageSlider;
import com.intechdev.tcommerce.Models.StockListModel;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Stock_List.StockListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private HomeContract.Presenter presenter;
    //private Slider slider;
    private SliderView slider;
    private TextView txt_homeFragment_showAllNew,
            txt_homeFragment_showAllTopSelling,
            txt_homeFragment_showAllHits;
    private RecyclerView rv_homeFragment_newProduct,
            rv_homeFragment_topSelling,
            rv_homeFragment_hits,
            rv_homeFragment_specialOffer;
    private Button btn_homeFragment_showAllSpecial;
    private HomeRecyclerAdapter adapter;
    private Fragment fragment;
    private FragmentTransaction transaction;
    private Bundle bundle;
    @SuppressLint("StaticFieldLeak")
    static Alert_Dialog dialog;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Alert_Dialog(getViewContext());
        String pls = getResources().getString(R.string.please_wait);
        String loading = getResources().getString(R.string.loading);
        dialog.showAlert(pls,loading);

        presenter = new HomePresenter(new TCommerceRepository(),getViewContext());
        presenter.getNewProduct();
        presenter.getHitsProduct();
        presenter.getSlider();


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupViews() {



        slider = rootView.findViewById(R.id.slider_homeFragment_slideShow);
        txt_homeFragment_showAllNew = rootView.findViewById(R.id.txt_homeFragment_showAllNew);
        txt_homeFragment_showAllTopSelling = rootView.findViewById(R.id.txt_homeFragment_showAllTopSelling);
        txt_homeFragment_showAllHits = rootView.findViewById(R.id.txt_homeFragment_showAllHits);
        rv_homeFragment_newProduct = rootView.findViewById(R.id.rv_homeFragment_newProduct);
        rv_homeFragment_topSelling = rootView.findViewById(R.id.rv_homeFragment_topSelling);
        rv_homeFragment_hits = rootView.findViewById(R.id.rv_homeFragment_hits);
        rv_homeFragment_specialOffer = rootView.findViewById(R.id.rv_homeFragment_specialOffer);
        btn_homeFragment_showAllSpecial = rootView.findViewById(R.id.btn_homeFragment_showAllSpecial);




        txt_homeFragment_showAllNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle  = new Bundle();
                bundle.putInt("flag",1);
                fragment = new StockListFragment();
                fragment.setArguments(bundle);
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        txt_homeFragment_showAllTopSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("flag",2);
                fragment = new StockListFragment();
                fragment.setArguments(bundle);
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        txt_homeFragment_showAllHits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("flag",3);
                fragment = new StockListFragment();
                fragment.setArguments(bundle);
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
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
        Log.i("LOG::::" , msg);
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
    public void showNewProduct(StockListModel list) {
        adapter = new HomeRecyclerAdapter(getViewContext() , list);
        rv_homeFragment_newProduct.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_homeFragment_newProduct.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void showTopSellProduct(StockListModel list) {
        adapter = new HomeRecyclerAdapter(getViewContext() , list);
        rv_homeFragment_topSelling.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_homeFragment_topSelling.setAdapter(adapter);
        dialog.dismiss();

    }

    @Override
    public void showHitsProduct(StockListModel list) {
        adapter = new HomeRecyclerAdapter(getViewContext() , list);
        rv_homeFragment_hits.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_homeFragment_hits.setAdapter(adapter);
        dialog.dismiss();


    }

    @Override
    public void showSlider(ItemImageSlider itemImageSlider) {
        TimerTask task = slider.getTimerTask();
        Timer timer = new Timer();
        timer.schedule(task,5000,2000);

        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < itemImageSlider.getItems().size(); i++) {
            urls.add(itemImageSlider.getItems().get(i).getIcon());
        }
        slider.setUrls(urls);

        dialog.dismiss();
    }
}
