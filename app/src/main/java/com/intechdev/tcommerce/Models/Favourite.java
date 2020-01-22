package com.intechdev.tcommerce.Models;

import java.util.List;

/**
 * Created by HBM on 01/08/2018.
 */

public class Favourite {

    private List<SubFav> items;
    private Setting Settings;

    public List<SubFav> getItems() {
        return items;
    }

    public void setItems(List<SubFav> items) {
        this.items = items;
    }

    public Setting getSettings() {
        return Settings;
    }

    public void setSettings(Setting settings) {
        Settings = settings;
    }

    public class SubFav{
        private int FavId;
        private int ProductId;
        private String LinkProduct;
        private String Price;
        private String ProductName;
        private int Status;
        private String TypeOfFav;
        private String Icon;

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }

        public String getLinkProduct() {
            return LinkProduct;
        }

        public void setLinkProduct(String linkProduct) {
            LinkProduct = linkProduct;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public String getTypeOfFav() {
            return TypeOfFav;
        }

        public void setTypeOfFav(String typeOfFav) {
            TypeOfFav = typeOfFav;
        }

        public int getFavId() {
            return FavId;
        }

        public void setFavId(int favId) {
            FavId = favId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int productId) {
            ProductId = productId;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }
    }

    public class Setting{
        private int ItemsCount;
        private int PagesCount;
        private int CurrPage;

        public int getItemsCount() {
            return ItemsCount;
        }

        public void setItemsCount(int itemsCount) {
            ItemsCount = itemsCount;
        }

        public int getPagesCount() {
            return PagesCount;
        }

        public void setPagesCount(int pagesCount) {
            PagesCount = pagesCount;
        }

        public int getCurrPage() {
            return CurrPage;
        }

        public void setCurrPage(int currPage) {
            CurrPage = currPage;
        }
    }
}
