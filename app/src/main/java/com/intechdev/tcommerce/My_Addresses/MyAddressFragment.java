package com.intechdev.tcommerce.My_Addresses;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.intechdev.tcommerce.Adapters.AddressAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Classes.GetProvinceAndCity;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemAddress;
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;

public class MyAddressFragment extends BaseFragment implements MyAddressContract.View {
    private MyAddressContract.Presenter presenter;

    private RecyclerView rvAddressList;
    private Button btnAdd,btnDialogAdd;
    private Spinner spDialogCity,spDialogProvince;
    private TextInputLayout tilDialogPostalCode,tilDialogAddress;
    private EditText edtDialogPostalCode,edtDialogAddress;


    private Bundle bundle;
    private Dialog dialogAddAddress;
    private List<String> provinces, cities;
    private String province,city;
    private ArrayAdapter<String> adapterProvince,adapterCity;
    private GetProvinceAndCity getProvinceAndCity;

    private int userId, flag,finalPrice,totalWeight;

    private AddressAdapter addressAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MyAddressPresenter(new TCommerceRepository());
        dialogAddAddress = new Dialog(getViewContext());
        dialogAddAddress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddAddress.setContentView(R.layout.dialog_add_address);
        dialogAddAddress.getWindow().setLayout(1200,1500);

        bundle = getArguments();
        if(getArguments().getInt("flag") != 0){
            flag = bundle.getInt("flag");
            finalPrice = bundle.getInt("finalPrice");
            userId = bundle.getInt("userId");
            totalWeight = bundle.getInt("totalWeight");
        }
        userId = bundle.getInt("userId",0);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_address;
    }

    @Override
    public void setupViews() {
        rvAddressList = rootView.findViewById(R.id.rv_myAddressFragment_addressList);

        spDialogCity = dialogAddAddress.findViewById(R.id.sp_dialogAddAddress_city);
        spDialogProvince = dialogAddAddress.findViewById(R.id.sp_dialogAddAddress_province);

        tilDialogAddress = dialogAddAddress.findViewById(R.id.til_dialogAddAddress_address);
        tilDialogPostalCode = dialogAddAddress.findViewById(R.id.til_dialogAddAddress_potalCode);

        edtDialogAddress = dialogAddAddress.findViewById(R.id.edt_dialogAddAddress_address);
        edtDialogPostalCode = dialogAddAddress.findViewById(R.id.edt_dialogAddAddress_postalCode);

        btnDialogAdd = dialogAddAddress.findViewById(R.id.btn_dialogAddAddress_save);
        btnAdd = rootView.findViewById(R.id.btn_myAddressFragment_addAddress);

        getProvinceAndCity = new GetProvinceAndCity();

        provinces = new ArrayList<>();
        cities = new ArrayList<>();
        provinces = getProvinceAndCity.getProvince();
        adapterProvince = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item, provinces);
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDialogProvince.setAdapter(adapterProvince);

        presenter.getAddress(userId);
        spDialogProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province = spDialogProvince.getSelectedItem().toString();
                cities = getProvinceAndCity.getCity(province);
                adapterCity = new ArrayAdapter<>(getViewContext(),android.R.layout.simple_spinner_item,cities);
                adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spDialogCity.setAdapter(adapterCity);

                spDialogCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        city = spDialogCity.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddAddress.show();
            }
        });

        btnDialogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String postalCode = edtDialogPostalCode.getText().toString();
                String add = edtDialogAddress.getText().toString();
                ItemAddress address = new ItemAddress();
                ItemAddress.SubAddress subAddress = new ItemAddress.SubAddress();
                List<ItemAddress.SubAddress> subAddresses = new ArrayList<>();
                subAddress.setUsrid(userId);
                subAddress.setAddressTxt(add);
                subAddress.setCity(city);
                subAddress.setProvince(province);
                subAddress.setPostalTxt(postalCode);
                subAddress.setAddressTitle("محل کار");
                subAddress.setTelTxt("12345678");
                subAddresses.add(subAddress);
                address.setItems(subAddresses);
//                address.getItems().get(0).setUsrid(userId);
//                address.getItems().get(0).setCity(city);
//                address.getItems().get(0).setProvince(province);
//                address.getItems().get(0).setAddressTitle(add);
//                address.getItems().get(0).setAddressTxt(add);
//                address.getItems().get(0).setPostalTxt(postalCode);
                presenter.addAddress(address);
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
    public void successAddAddress(Result result) {
        int id = result.getResult();
        if(id > 0){
            dialogAddAddress.dismiss();
            presenter.getAddress(userId);
            showMessage("آدرس با موفقیت ثبت گردید");

        }
    }

    @Override
    public void getAddresses(ItemAddress address) {
        if(address.getItems() == null){
            showMessage("هیچ آدرسی ثبت نکرده اید");
        }else {
            addressAdapter = new AddressAdapter(getViewContext(), address, this,  adapterProvince,userId,finalPrice,totalWeight,flag);
            rvAddressList.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
            rvAddressList.setAdapter(addressAdapter);
        }
    }

    @Override
    public void setEditAddres(ItemAddress addres) {
        if(addres != null){
            presenter.editAddress(addres);


        }
    }

    @Override
    public void deleteAddress(int usrId, int addressId) {
        presenter.deleteAddress(usrId, addressId);
    }

    @Override
    public void successEditAddress(Result result) {
        if(result.getResult() > 0){
            showMessage("آدرس با موفقیت ویرایش شد");
        }
    }

    @Override
    public void successDeleteAddress(Result result) {
        if(result.getResult() > 0){
            showMessage("آدرس با موفقیت پاک شد");
            presenter.getAddress(userId);
        }
    }
}
