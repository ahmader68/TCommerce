package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ItemAddress {
    private List<SubAddress> items;

    public List<SubAddress> getItems() {
        return items;
    }

    public void setItems(List<SubAddress> items) {
        this.items = items;
    }

    public static class SubAddress{
       private int id;

       private int usrid;

       private String AddressTxt;

       private String PostalTxt;

       private String AddressTitle;

       private String City;

       private String Province;

       private String telTxt;

       private int result;

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public int getUsrid() {
           return usrid;
       }

       public void setUsrid(int usrid) {
           this.usrid = usrid;
       }

       public String getAddressTxt() {
           return AddressTxt;
       }

       public void setAddressTxt(String addressTxt) {
           AddressTxt = addressTxt;
       }

       public String getPostalTxt() {
           return PostalTxt;
       }

       public void setPostalTxt(String postalTxt) {
           PostalTxt = postalTxt;
       }

       public String getAddressTitle() {
           return AddressTitle;
       }

       public void setAddressTitle(String addressTitle) {
           AddressTitle = addressTitle;
       }

       public String getCity() {
           return City;
       }

       public void setCity(String city) {
           City = city;
       }

       public String getProvince() {
           return Province;
       }

       public void setProvince(String province) {
           Province = province;
       }

       public String getTelTxt() {
           return telTxt;
       }

       public void setTelTxt(String telTxt) {
           this.telTxt = telTxt;
       }

       public int getResult() {
           return result;
       }

       public void setResult(int result) {
           this.result = result;
       }
   }
}
