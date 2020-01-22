package com.intechdev.tcommerce.Payment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.My_Addresses.MyAddressFragment;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends BaseFragment implements PaymentContract.View {

    private PaymentContract.Presenter presenter;

    private TextView
                    txtTotalCost,
                    txtTotalWeight,
                    txtPriceForPay;

    private Spinner spSendingMethod,spPayMethod;

    private EditText edtExplain;

    private Button btnPay;

    private List<String> paymentMethodList,sendMethodList;

    private ArrayAdapter<String> paymentMethodAdapter,sendMethodAdapter;

    private int totalPrice,totalWeight,finalPrice,userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            Bundle bundle = getArguments();
            totalPrice = bundle.getInt("totalPrice");
            totalWeight = bundle.getInt("totalWeight");
            finalPrice = bundle.getInt("finalPrice");
            userId = bundle.getInt("userId");
        }
        presenter = new PaymentPresenter(new TCommerceRepository());
        paymentMethodList = new ArrayList<>();
        sendMethodList = new ArrayList<>();

        paymentMethodList.add("نقدی");
        paymentMethodList.add("اینترنتی");

        sendMethodList.add("پیک شهری");
        sendMethodList.add("پست");
        sendMethodList.add("باربری");
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    public void setupViews() {

        txtPriceForPay = rootView.findViewById(R.id.txt_paymentFragment_price);
        txtTotalCost = rootView.findViewById(R.id.txt_paymentFragment_totalSum);
        txtTotalWeight = rootView.findViewById(R.id.txt_paymentFragment_totalWeight);
        spPayMethod = rootView.findViewById(R.id.sp_paymentFragment_payMethod);
        spSendingMethod = rootView.findViewById(R.id.sp_paymentFragment_sendingMethod);
        edtExplain = rootView.findViewById(R.id.edt_paymentFragment_explainForAdmin);
        btnPay = rootView.findViewById(R.id.btn_paymentFragment_pay);

        paymentMethodAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,paymentMethodList);
        paymentMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPayMethod.setAdapter(paymentMethodAdapter);

        sendMethodAdapter = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,sendMethodList);
        sendMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSendingMethod.setAdapter(sendMethodAdapter);

        txtTotalWeight.setText(String.valueOf(totalWeight));
        txtTotalCost.setText(String.valueOf(totalPrice));
        txtPriceForPay.setText(String.valueOf(finalPrice));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Waiting for Bank", Toast.LENGTH_SHORT).show();
                int flag = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("flag",flag);
                bundle.putInt("finalPrice",finalPrice);
                bundle.putInt("totalWeight",totalWeight);
                bundle.putInt("userId",userId);
                Fragment myAddressFragment = new MyAddressFragment();
                myAddressFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,myAddressFragment);
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
        Toast.makeText(getViewContext() , msg , Toast.LENGTH_SHORT).show();
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
