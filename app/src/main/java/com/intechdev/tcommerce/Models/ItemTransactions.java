package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class ItemTransactions {

    List<SubItemTransactions> items;

    public List<SubItemTransactions> getItems() {
        return items;
    }

    public void setItems(List<SubItemTransactions> items) {
        this.items = items;
    }

    public static class SubItemTransactions{
        private int RowNumber;
        private String DateSend;
        private String TrackingCode;
        private String Price;
        private String PayType;
        private String PardakhtBabate;
        private String PaySerialNo;
        private int IdOrder;
        private String LinkDetailOrder;

        public int getRowNumber() {
            return RowNumber;
        }

        public void setRowNumber(int rowNumber) {
            RowNumber = rowNumber;
        }

        public String getDateSend() {
            return DateSend;
        }

        public void setDateSend(String dateSend) {
            DateSend = dateSend;
        }

        public String getTrackingCode() {
            return TrackingCode;
        }

        public void setTrackingCode(String trackingCode) {
            TrackingCode = trackingCode;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getPayType() {
            return PayType;
        }

        public void setPayType(String payType) {
            PayType = payType;
        }

        public String getPardakhtBabate() {
            return PardakhtBabate;
        }

        public void setPardakhtBabate(String pardakhtBabate) {
            PardakhtBabate = pardakhtBabate;
        }

        public String getPaySerialNo() {
            return PaySerialNo;
        }

        public void setPaySerialNo(String paySerialNo) {
            PaySerialNo = paySerialNo;
        }

        public int getIdOrder() {
            return IdOrder;
        }

        public void setIdOrder(int idOrder) {
            IdOrder = idOrder;
        }

        public String getLinkDetailOrder() {
            return LinkDetailOrder;
        }

        public void setLinkDetailOrder(String linkDetailOrder) {
            LinkDetailOrder = linkDetailOrder;
        }
    }
}
