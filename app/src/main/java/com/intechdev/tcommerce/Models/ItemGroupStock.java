package com.intechdev.tcommerce.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by HBM on 18/06/2018.
 */

public class ItemGroupStock implements Parcelable {

    private List<Items> items;

    public static final Creator<ItemGroupStock> CREATOR = new Creator<ItemGroupStock>() {
        @Override
        public ItemGroupStock createFromParcel(Parcel in) {
            return new ItemGroupStock(in);
        }

        @Override
        public ItemGroupStock[] newArray(int size) {
            return new ItemGroupStock[size];
        }
    };

    public ItemGroupStock(Parcel in) {

    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
    }

    public class Items implements Parcelable {

        private long id;
        private String name;
        private String icon;
        private long parentId;
        private long tabId;


        protected Items(Parcel in) {
            id = in.readLong();
            name = in.readString();
            icon = in.readString();
            parentId = in.readLong();
            tabId = in.readLong();
        }

        public final Creator<Items> CREATOR = new Creator<Items>() {
            @Override
            public Items createFromParcel(Parcel in) {
                return new Items(in);
            }

            @Override
            public Items[] newArray(int size) {
                return new Items[size];
            }
        };

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public long getParentId() {
            return parentId;
        }

        public void setParentId(long parentId) {
            this.parentId = parentId;
        }

        public long getTabId() {
            return tabId;
        }

        public void setTabId(long tabId) {
            this.tabId = tabId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(name);
            dest.writeString(icon);
            dest.writeLong(parentId);
            dest.writeLong(tabId);
        }
    }
}
