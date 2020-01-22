package com.intechdev.tcommerce.Stock_Details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.example.sliderviewlibrary.SliderView;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Home.HomeFragment;
import com.intechdev.tcommerce.Models.ItemStockDetails;
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StockDetailsFragment extends BaseFragment implements StockDetailsContract.View {

    private StockDetailsContract.Presenter presenter;

    private long productId;

    private SliderView slider;

    private TextView txtModel,txtPrice;

    private ImageView imgShare;

    private Button btnAdd,btnProperties,btnComments;

    private LikeButton btnLike;

    private Spinner spCount;

    private List<String> counts;

    private int count,userId;

    private SharedPreferences sharedPreferences;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new StockDetailsPresenter(new TCommerceRepository());
        Bundle bundle = getArguments();
        productId = bundle.getLong("productId");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userId",0);
        counts = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            counts.add(String.valueOf(i));
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_stock_details;
    }

    @Override
    public void setupViews() {

        slider = rootView.findViewById(R.id.slider_stockDetailsFragment_slider);

        txtModel = rootView.findViewById(R.id.txt_stockDetailsFragment_model);
        txtPrice = rootView.findViewById(R.id.txt_stockDetailsFragment_price);
        imgShare = rootView.findViewById(R.id.img_stockDetailsFragment_share);

        btnAdd = rootView.findViewById(R.id.btn_stockDetailsFragment_addToCard);
        btnComments = rootView.findViewById(R.id.btn_stockDetailsFragment_comments);
        btnProperties = rootView.findViewById(R.id.btn_stockDetailsFragment_properties);

        spCount = rootView.findViewById(R.id.sp_stockDetailsFragment_count);

        btnLike = rootView.findViewById(R.id.btn_stockDetailsFragment_like);

        ArrayAdapter<String> countAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,counts);
        countAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCount.setAdapter(countAdapter);

        spCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = Integer.parseInt(spCount.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        presenter.getItemStockDetails(productId);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToBasket((int)productId,count,userId);
            }
        });

        btnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                presenter.addToFav(userId,(int)productId);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                presenter.deleteFav(userId,(int)productId);
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
    public void showStockDetails(ItemStockDetails.ItemStockDetailsObject itemStockDetailsObject) {
        TimerTask task = slider.getTimerTask();
        Timer timer = new Timer();
        timer.schedule(task,5000,2000);
        ArrayList<String> urls = new ArrayList<>();
        for(int i = 0;i < itemStockDetailsObject.getItems().size(); i++){
            for(int j = 0; j < itemStockDetailsObject.getItems().get(i).getAlbum().size(); j++){
                urls.add(itemStockDetailsObject.getItems().get(i).getAlbum().get(j).getIcon());
            }
            urls.add(itemStockDetailsObject.getItems().get(i).getIcon());
        }

        slider.setUrls(urls);
        txtModel.setText(itemStockDetailsObject.getItems().get(0).getName());
        txtPrice.setText(String.valueOf(itemStockDetailsObject.getItems().get(0).getPrice()));
    }

    @Override
    public void addToBasketSuccess(Result result) {
        if(result.getResult() == -100){
            showMessage("کالا ثبت نشد، مجدد تلاش کنید");
        }else if(result.getResult() > 0){
            Toast.makeText(getViewContext(), "کالا با موفقیت ثبت گردید", Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment fragment = new HomeFragment();
            transaction.replace(R.id.frm_mainFragment_mainLayout,fragment);
            transaction.commit();
        }
    }

    @Override
    public void addToFav(Result result) {
        if(result.getResult() > 0){
            showMessage("کالا با موفقیت به علاقمندی ها اضافه شد");
        }
    }

    @Override
    public void deleteFav(Result result) {
        if(result.getResult() > 0){
            showMessage("کالا با موفقیت از علاقمندی ها حذف گردید");
        }
    }
}
