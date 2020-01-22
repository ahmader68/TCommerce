package com.intechdev.tcommerce.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemOrdersBasket {

    private List<SubItemOrderBasket> items;

    public List<SubItemOrderBasket> getItems() {
        return items;
    }

    public void setItems(List<SubItemOrderBasket> items) {
        this.items = items;
    }

    public static class SubItemOrderBasket{
        private int ID;
        private int ProductId;
        private int usrid;
        private int ProductMojoody;
        private int ProductDiscount;
        private boolean ProductDiscountIsPercent;
        private String ProductTitle;
        private int Discount;
        private boolean DiscountIsPercent;
        private String ProductBrief;
        private String ProductAttributes;
        private String ProductContent;
        private String Productimage;
        private String ProductUrl;
        private int OrderCount;
        private int unitPrice;
        private int unitWeight;
        private int totalPrice;
        private int totalWeight;


        protected SubItemOrderBasket(Parcel in) {
            ID = in.readInt();
            ProductId = in.readInt();
            usrid = in.readInt();
            ProductMojoody = in.readInt();
            ProductDiscount = in.readInt();
            ProductDiscountIsPercent = in.readByte() != 0;
            ProductTitle = in.readString();
            Discount = in.readInt();
            DiscountIsPercent = in.readByte() != 0;
            ProductBrief = in.readString();
            ProductAttributes = in.readString();
            ProductContent = in.readString();
            Productimage = in.readString();
            ProductUrl = in.readString();
            OrderCount = in.readInt();
            unitPrice = in.readInt();
            unitWeight = in.readInt();
            totalPrice = in.readInt();
            totalWeight = in.readInt();
        }



        public int getUsrid() {
            return usrid;
        }

        public void setUsrid(int usrid) {
            this.usrid = usrid;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int productId) {
            ProductId = productId;
        }

        public int getProductMojoody() {
            return ProductMojoody;
        }

        public void setProductMojoody(int productMojoody) {
            ProductMojoody = productMojoody;
        }

        public int getProductDiscount() {
            return ProductDiscount;
        }

        public void setProductDiscount(int productDiscount) {
            ProductDiscount = productDiscount;
        }

        public boolean isProductDiscountIsPercent() {
            return ProductDiscountIsPercent;
        }

        public void setProductDiscountIsPercent(boolean productDiscountIsPercent) {
            ProductDiscountIsPercent = productDiscountIsPercent;
        }

        public String getProductTitle() {
            return ProductTitle;
        }

        public void setProductTitle(String productTitle) {
            ProductTitle = productTitle;
        }

        public int getDiscount() {
            return Discount;
        }

        public void setDiscount(int discount) {
            Discount = discount;
        }

        public boolean isDiscountIsPercent() {
            return DiscountIsPercent;
        }

        public void setDiscountIsPercent(boolean discountIsPercent) {
            DiscountIsPercent = discountIsPercent;
        }

        public String getProductBrief() {
            return ProductBrief;
        }

        public void setProductBrief(String productBrief) {
            ProductBrief = productBrief;
        }

        public String getProductAttributes() {
            return ProductAttributes;
        }

        public void setProductAttributes(String productAttributes) {
            ProductAttributes = productAttributes;
        }

        public String getProductContent() {
            return ProductContent;
        }

        public void setProductContent(String productContent) {
            ProductContent = productContent;
        }

        public String getProductimage() {
            return Productimage;
        }

        public void setProductimage(String productimage) {
            Productimage = productimage;
        }

        public String getProductUrl() {
            return ProductUrl;
        }

        public void setProductUrl(String productUrl) {
            ProductUrl = productUrl;
        }

        public int getOrderCount() {
            return OrderCount;
        }

        public void setOrderCount(int orderCount) {
            OrderCount = orderCount;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getUnitWeight() {
            return unitWeight;
        }

        public void setUnitWeight(int unitWeight) {
            this.unitWeight = unitWeight;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(int totalWeight) {
            this.totalWeight = totalWeight;
        }

    }
}
