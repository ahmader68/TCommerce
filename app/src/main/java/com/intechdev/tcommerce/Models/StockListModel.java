package com.intechdev.tcommerce.Models;

import java.util.List;

/**
 * Created by HBM on 23/04/2018.
 */

public class StockListModel {

    public static final int VIEW_SQUARE = 0;
    public static final int VIEW_REQTANGLE = 1;

    private List<Product> items;
    private static int type;

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public class Product {
        private Long id;
        private String name;
        private String stockDescription;
        private String price;
        private String priceOffer;
        private String icon;
        private String informMe;

/*
    public StockListModel(Long id, String stockName, String stockDescription, String price, String priceOffer, String stockUrlImageSmall, String informMe, int type) {
        this.id = id;
        this.stockName = stockName;
        this.stockDescription = stockDescription;
        this.price = price;
        this.priceOffer = priceOffer;
        this.stockUrlImageSmall = stockUrlImageSmall;
        this.informMe = informMe;
        this.type = type;
    }

 */

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getStockDescription() {
            return stockDescription;
        }

        public void setStockDescription(String stockDescription) {
            this.stockDescription = stockDescription;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPriceOffer() {
            return priceOffer;
        }

        public void setPriceOffer(String priceOffer) {
            this.priceOffer = priceOffer;
        }

        public String getInformMe() {
            return informMe;
        }

        public void setInformMe(String informMe) {
            this.informMe = informMe;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
