package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 15/07/2018.
 */

public class ItemListStock {

    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public class Items {
        private String id;
        private String name;
        private String price;
        private String rate;
        private String code;
        private String archiveDate;
        private String brand;
        private String BuyPrice;
        private String CreateDate;
        private String CreatorFullName;
        private String Description;
        private String brief;
        private String tag;
        private String Language;
        private String CategoryId;
        private String CategoryName;
        private String SubCategoryId;
        private String Code;
        private String Quality;
        private String Quantity;
        private String Discount;
        private String Brand;
        private String Model;
        private String Series;
        private String PublishDate;
        private String LastVisitDate;
        private String createDateEN;
        private String publishDateEN;
        private String LastVisitDateEN;
        private String VisitCount;
        private String IsNew;
        private String IsSpecific;
        private String IsImportant;
        private String status;
        private String icon;
        private String InformMe;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getQuality() {
            return Quality;
        }

        public void setQuality(String quality) {
            Quality = quality;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String quantity) {
            Quantity = quantity;
        }

        public String getDiscount() {
            return Discount;
        }

        public void setDiscount(String discount) {
            Discount = discount;
        }

        public String getArchiveDate() {
            return archiveDate;
        }

        public void setArchiveDate(String archiveDate) {
            this.archiveDate = archiveDate;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            Model = model;
        }

        public String getSeries() {
            return Series;
        }

        public void setSeries(String series) {
            Series = series;
        }

        public String getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(String publishDate) {
            PublishDate = publishDate;
        }

        public String getLastVisitDate() {
            return LastVisitDate;
        }

        public void setLastVisitDate(String lastVisitDate) {
            LastVisitDate = lastVisitDate;
        }

        public String getCreateDateEN() {
            return createDateEN;
        }

        public void setCreateDateEN(String createDateEN) {
            this.createDateEN = createDateEN;
        }

        public String getPublishDateEN() {
            return publishDateEN;
        }

        public void setPublishDateEN(String publishDateEN) {
            this.publishDateEN = publishDateEN;
        }

        public String getLastVisitDateEN() {
            return LastVisitDateEN;
        }

        public void setLastVisitDateEN(String lastVisitDateEN) {
            LastVisitDateEN = lastVisitDateEN;
        }

        public String getVisitCount() {
            return VisitCount;
        }

        public void setVisitCount(String visitCount) {
            VisitCount = visitCount;
        }

        public String getIsNew() {
            return IsNew;
        }

        public void setIsNew(String isNew) {
            IsNew = isNew;
        }

        public String getIsSpecific() {
            return IsSpecific;
        }

        public void setIsSpecific(String isSpecific) {
            IsSpecific = isSpecific;
        }

        public String getIsImportant() {
            return IsImportant;
        }

        public void setIsImportant(String isImportant) {
            IsImportant = isImportant;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getInformMe() {
            return InformMe;
        }

        public void setInformMe(String informMe) {
            InformMe = informMe;
        }

        public String getBuyPrice() {
            return BuyPrice;
        }

        public void setBuyPrice(String buyPrice) {
            BuyPrice = buyPrice;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getCreatorFullName() {
            return CreatorFullName;
        }

        public void setCreatorFullName(String creatorFullName) {
            CreatorFullName = creatorFullName;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String language) {
            Language = language;
        }

        public String getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(String categoryId) {
            CategoryId = categoryId;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public String getSubCategoryId() {
            return SubCategoryId;
        }

        public void setSubCategoryId(String subCategoryId) {
            SubCategoryId = subCategoryId;
        }
    }
}
