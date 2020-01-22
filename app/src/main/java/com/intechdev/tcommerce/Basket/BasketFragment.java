package com.intechdev.tcommerce.Basket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.ProductInBasketAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemOrdersBasket;
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.Payment.PaymentFragment;
import com.intechdev.tcommerce.R;

public class BasketFragment extends BaseFragment implements BasketContract.View {
    private BasketContract.Presenter presenter;

    private TextView txtTotalPrice;

    private RecyclerView rvBasketList;

    private Button btnFinalPay;

    private ProductInBasketAdapter productInBasketAdapter;

    private SharedPreferences sharedPreferences;

    private ItemOrdersBasket itemOrdersBasket;

    private int
            userId,
            totalPrice = 0,
            finalPrice = 0,
            totalWeight = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BasketPresenter(new TCommerceRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userId", 0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_basket;
    }

    @Override
    public void setupViews() {

        txtTotalPrice = rootView.findViewById(R.id.txt_basketFragment_totalPrice);
        rvBasketList = rootView.findViewById(R.id.rv_basketFragment_productList);
        btnFinalPay = rootView.findViewById(R.id.btn_basketFragment_pay);
        presenter.getOrdersInBasket(userId);

        btnFinalPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOrdersBasket.getItems() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("totalPrice", totalPrice);
                    bundle.putInt("totalWeight", totalWeight);
                    bundle.putInt("finalPrice", finalPrice);
                    bundle.putInt("userId", userId);
                    Fragment fragment = new PaymentFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction;
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                    transaction.commit();
                }else{
                    showMessage("سبد خرید شما خالی است");
                }

            }
        });


    }

    @Override
    public void deleteFromBasketSuccessful(Result result) {
        if (result.getResult() > 0) {
            showMessage("کالا با موفقیت از سبد خرید حذف گردید");
        }
    }

    @Override
    public void deleteFromBasket(int usrid, int OrderId,int position,ItemOrdersBasket itemOrdersBasket) {
        itemOrdersBasket.getItems().remove(position);
        rvBasketList.removeViewAt(position);
        productInBasketAdapter.notifyItemRemoved(position);
        productInBasketAdapter.notifyItemRangeChanged(position,itemOrdersBasket.getItems().size());
        totalPrice = 0;
        totalWeight = 0;
        for (int i = 0; i < itemOrdersBasket.getItems().size(); i++) {

            totalPrice += itemOrdersBasket.getItems().get(i).getTotalPrice();
            totalWeight += itemOrdersBasket.getItems().get(i).getTotalWeight();

        }
        txtTotalPrice.setText(String.valueOf(totalPrice));
        presenter.deleteFromBasket(usrid, OrderId);
    }

    @Override
    public void showOrdersInBasket(ItemOrdersBasket itemOrdersBasket) {
        this.itemOrdersBasket = itemOrdersBasket;
        if(itemOrdersBasket.getItems() != null) {
            productInBasketAdapter = new ProductInBasketAdapter(getViewContext(), this, itemOrdersBasket);
            rvBasketList.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
            rvBasketList.setAdapter(productInBasketAdapter);
            for (int i = 0; i < itemOrdersBasket.getItems().size(); i++) {

                totalPrice += itemOrdersBasket.getItems().get(i).getTotalPrice();
                totalWeight += itemOrdersBasket.getItems().get(i).getTotalWeight();

            }
            finalPrice = totalPrice;
            txtTotalPrice.setText(String.valueOf(totalPrice));
        }else{
            showMessage("سبد خرید شما خالی است");
        }

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
