package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/08/2018.
 */

public class ItemDiscounts {
    private List<SubItemDiscounts> items;

    public List<SubItemDiscounts> getItems() {
        return items;
    }

    public void setItems(List<SubItemDiscounts> items) {
        this.items = items;
    }

    public static class SubItemDiscounts{
        private int NumberDiscount;
        private int RoWNumber;
        private String Title;
        private String Description;
        private String DiscountCode;
        private String Status;
        private String DiscountTracking;
        private String DiscountCreateDate;
        private String MizanTakhfif;
        private String OrderDetailsLink;
        private int OrderId;

        public int getNumberDiscount() {
            return NumberDiscount;
        }

        public void setNumberDiscount(int numberDiscount) {
            NumberDiscount = numberDiscount;
        }

        public int getRoWNumber() {
            return RoWNumber;
        }

        public void setRoWNumber(int roWNumber) {
            RoWNumber = roWNumber;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getDiscountCode() {
            return DiscountCode;
        }

        public void setDiscountCode(String discountCode) {
            DiscountCode = discountCode;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getDiscountTracking() {
            return DiscountTracking;
        }

        public void setDiscountTracking(String discountTracking) {
            DiscountTracking = discountTracking;
        }

        public String getDiscountCreateDate() {
            return DiscountCreateDate;
        }

        public void setDiscountCreateDate(String discountCreateDate) {
            DiscountCreateDate = discountCreateDate;
        }

        public String getMizanTakhfif() {
            return MizanTakhfif;
        }

        public void setMizanTakhfif(String mizanTakhfif) {
            MizanTakhfif = mizanTakhfif;
        }

        public String getOrderDetailsLink() {
            return OrderDetailsLink;
        }

        public void setOrderDetailsLink(String orderDetailsLink) {
            OrderDetailsLink = orderDetailsLink;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int orderId) {
            OrderId = orderId;
        }
    }
}
