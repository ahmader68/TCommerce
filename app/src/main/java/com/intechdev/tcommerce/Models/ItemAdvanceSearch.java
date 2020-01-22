package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemAdvanceSearch {
    //public static final int VIEW_CHECK_BOX = 0;
    //public static final int VIEW_COLOR_BOX = 1;
    //public static final int VIEW_FROM_TO = 2;

    //private int itemType;
    //public int position;

    private List<ItemMain> items;

    public List<ItemMain> getItems() {
        return items;
    }

    public void setItems(List<ItemMain> items) {
        this.items = items;
    }

    public static class ItemMain {
        private long id;
        private String name;
        private String Brief;
        private String Tag;
        private String ParentId;
        private String IsImportant;
        private String icon;
        private String Type;
        private String ColorCode;
        private String Price1;
        private String Price2;
        private String isSelected;

        public ItemMain(long id, String name, String brief, String tag, String parentId, String isImportant, String icon, String type, String colorCode, String price1, String price2, String isSelected) {
            this.id = id;
            this.name = name;
            Brief = brief;
            Tag = tag;
            ParentId = parentId;
            IsImportant = isImportant;
            this.icon = icon;
            Type = type;
            ColorCode = colorCode;
            Price1 = price1;
            Price2 = price2;
            this.isSelected = isSelected;
        }

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

        public String getBrief() {
            return Brief;
        }

        public void setBrief(String brief) {
            Brief = brief;
        }

        public String getTag() {
            return Tag;
        }

        public void setTag(String tag) {
            Tag = tag;
        }

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String parentId) {
            ParentId = parentId;
        }

        public String getIsImportant() {
            return IsImportant;
        }

        public void setIsImportant(String isImportant) {
            IsImportant = isImportant;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getColorCode() {
            return ColorCode;
        }

        public void setColorCode(String colorCode) {
            ColorCode = colorCode;
        }

        public String getPrice1() {
            return Price1;
        }

        public void setPrice1(String price1) {
            Price1 = price1;
        }

        public String getPrice2() {
            return Price2;
        }

        public void setPrice2(String price2) {
            Price2 = price2;
        }

        public String getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(String isSelected) {
            this.isSelected = isSelected;
        }
    }
}
