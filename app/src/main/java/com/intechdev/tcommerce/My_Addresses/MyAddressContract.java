package com.intechdev.tcommerce.My_Addresses;

import com.intechdev.tcommerce.Base.BasePresenter;
import com.intechdev.tcommerce.Base.BaseView;
import com.intechdev.tcommerce.Models.ItemAddress;
import com.intechdev.tcommerce.Models.Result;

import java.util.List;

public interface MyAddressContract {

    interface View extends BaseView{

        void successAddAddress(Result result);
        void getAddresses(ItemAddress address);
        void setEditAddres(ItemAddress addres);
        void deleteAddress(int usrId, int addressId);
        void successEditAddress(Result result);
        void successDeleteAddress(Result result);
    }
    interface Presenter extends BasePresenter<View>{

        void addAddress(ItemAddress address);
        void getAddress(int userId);
        void deleteAddress(int userId, int addressId);
        void editAddress(ItemAddress address);
    }
}
