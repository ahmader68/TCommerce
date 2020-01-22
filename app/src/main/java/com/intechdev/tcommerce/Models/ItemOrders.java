package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ItemOrders {

    private List<SubOrders> items;

    public List<SubOrders> getItems() {
        return items;
    }

    public void setItems(List<SubOrders> items) {
        this.items = items;
    }

    public static class SubOrders{
        int id;
        int BuyerId ;
        String BuyerMobile;
        String BuyerFullName;
        int BasketType;
        int PayType;
        String TotalToPay;
        String PostAmount;
        int PostType;
        String TotalAmount;
        int TotalItemCount;
        String TrackingCode;
        String ConfirmDate;
        String CreateDate;
        String DeliverDate;
        String ExpireDate;
        String OrderDate;
        String ReadyDate;
        String Status;
        String Unread;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBuyerId() {
            return BuyerId;
        }

        public void setBuyerId(int buyerId) {
            BuyerId = buyerId;
        }

        public String getBuyerMobile() {
            return BuyerMobile;
        }

        public void setBuyerMobile(String buyerMobile) {
            BuyerMobile = buyerMobile;
        }

        public String getBuyerFullName() {
            return BuyerFullName;
        }

        public void setBuyerFullName(String buyerFullName) {
            BuyerFullName = buyerFullName;
        }

        public int getBasketType() {
            return BasketType;
        }

        public void setBasketType(int basketType) {
            BasketType = basketType;
        }

        public int getPayType() {
            return PayType;
        }

        public void setPayType(int payType) {
            PayType = payType;
        }

        public String getTotalToPay() {
            return TotalToPay;
        }

        public void setTotalToPay(String totalToPay) {
            TotalToPay = totalToPay;
        }

        public String getPostAmount() {
            return PostAmount;
        }

        public void setPostAmount(String postAmount) {
            PostAmount = postAmount;
        }

        public int getPostType() {
            return PostType;
        }

        public void setPostType(int postType) {
            PostType = postType;
        }

        public String getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            TotalAmount = totalAmount;
        }

        public int getTotalItemCount() {
            return TotalItemCount;
        }

        public void setTotalItemCount(int totalItemCount) {
            TotalItemCount = totalItemCount;
        }

        public String getTrackingCode() {
            return TrackingCode;
        }

        public void setTrackingCode(String trackingCode) {
            TrackingCode = trackingCode;
        }

        public String getConfirmDate() {
            return ConfirmDate;
        }

        public void setConfirmDate(String confirmDate) {
            ConfirmDate = confirmDate;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getDeliverDate() {
            return DeliverDate;
        }

        public void setDeliverDate(String deliverDate) {
            DeliverDate = deliverDate;
        }

        public String getExpireDate() {
            return ExpireDate;
        }

        public void setExpireDate(String expireDate) {
            ExpireDate = expireDate;
        }

        public String getOrderDate() {
            return OrderDate;
        }

        public void setOrderDate(String orderDate) {
            OrderDate = orderDate;
        }

        public String getReadyDate() {
            return ReadyDate;
        }

        public void setReadyDate(String readyDate) {
            ReadyDate = readyDate;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getUnread() {
            return Unread;
        }

        public void setUnread(String unread) {
            Unread = unread;
        }
    }


}
