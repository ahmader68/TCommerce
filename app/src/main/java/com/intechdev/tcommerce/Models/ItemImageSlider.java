package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 13/06/2018.
 */

public class ItemImageSlider {

    List<SliderInfo> items;

    public List<SliderInfo> getItems() {
        return items;
    }

    public void setItems(List<SliderInfo> items) {
        this.items = items;
    }

    public class SliderInfo {
        long id;
        private String name;
        private String order;
        private String url;
        private String icon;
        private String createDate;
        private String createDateEN;
        private String catId;
        private String scatId;
        private String link;
        private String LinkTarget;
        private String LinkTo;
        private String Language;
        private String IsImportant;
        private String Status;
        private String CategoryName;
        private String StatusName;
        private String Description;
        private String visitCount;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateDateEN() {
            return createDateEN;
        }

        public void setCreateDateEN(String createDateEN) {
            this.createDateEN = createDateEN;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getScatId() {
            return scatId;
        }

        public void setScatId(String scatId) {
            this.scatId = scatId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLinkTarget() {
            return LinkTarget;
        }

        public void setLinkTarget(String linkTarget) {
            LinkTarget = linkTarget;
        }

        public String getLinkTo() {
            return LinkTo;
        }

        public void setLinkTo(String linkTo) {
            LinkTo = linkTo;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String language) {
            Language = language;
        }

        public String isImportant() {
            return IsImportant;
        }

        public void setImportant(String important) {
            IsImportant = important;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public String getStatusName() {
            return StatusName;
        }

        public void setStatusName(String statusName) {
            StatusName = statusName;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getVisitCount() {
            return visitCount;
        }

        public void setVisitCount(String visitCount) {
            this.visitCount = visitCount;
        }
    }
}
