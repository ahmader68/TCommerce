package com.intechdev.tcommerce.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/08/2018.
 */

public class ItemBlog {

    private Setting settings;
    private List<SubItemBlog> items;

    public Setting getSettings() {
        return settings;
    }

    public void setSettings(Setting settings) {
        this.settings = settings;
    }

    public List<SubItemBlog> getItems() {
        return items;
    }

    public void setItems(List<SubItemBlog> items) {
        this.items = items;
    }

    public class SubItemBlog{
        private int id;
        private String name;
        private int rate;
        private String icon;
        private String url;
        private String createDate;
        private String publishDate;
        private String archiveDate;
        private String createDateEN;
        private String publishDateEN;
        private String archiveDateEN;
        private int catId;
        private int isArchive;
        private int scatId;
        private String catName;
        private String scatName;
        private int cmdCount;
        private int visitCount;
        private String writer;
        private String reference;
        private String referenceLink;
        private String tags;
        private String brief;
        private List<Album> albums;
        private String nav;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getArchiveDate() {
            return archiveDate;
        }

        public void setArchiveDate(String archiveDate) {
            this.archiveDate = archiveDate;
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

        public String getArchiveDateEN() {
            return archiveDateEN;
        }

        public void setArchiveDateEN(String archiveDateEN) {
            this.archiveDateEN = archiveDateEN;
        }

        public int getCatId() {
            return catId;
        }

        public void setCatId(int catId) {
            this.catId = catId;
        }

        public int getIsArchive() {
            return isArchive;
        }

        public void setIsArchive(int isArchive) {
            this.isArchive = isArchive;
        }

        public int getScatId() {
            return scatId;
        }

        public void setScatId(int scatId) {
            this.scatId = scatId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getScatName() {
            return scatName;
        }

        public void setScatName(String scatName) {
            this.scatName = scatName;
        }

        public int getCmdCount() {
            return cmdCount;
        }

        public void setCmdCount(int cmdCount) {
            this.cmdCount = cmdCount;
        }

        public int getVisitCount() {
            return visitCount;
        }

        public void setVisitCount(int visitCount) {
            this.visitCount = visitCount;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getReferenceLink() {
            return referenceLink;
        }

        public void setReferenceLink(String referenceLink) {
            this.referenceLink = referenceLink;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public List<Album> getAlbums() {
            return albums;
        }

        public void setAlbums(List<Album> albums) {
            this.albums = albums;
        }

        public String getNav() {
            return nav;
        }

        public void setNav(String nav) {
            this.nav = nav;
        }
    }

    public class Album implements Parcelable {

        private int id;
        private String name;
        private String brief;
        private String icon;
        private String mainImage;

        protected Album(Parcel in) {
            id = in.readInt();
            name = in.readString();
            brief = in.readString();
            icon = in.readString();
            mainImage = in.readString();
        }

        public final Creator<Album> CREATOR = new Creator<Album>() {
            @Override
            public Album createFromParcel(Parcel in) {
                return new Album(in);
            }

            @Override
            public Album[] newArray(int size) {
                return new Album[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(brief);
            dest.writeString(icon);
            dest.writeString(mainImage);
        }
    }

    private class Setting{
        private int itemsCount;
        private int pagesCount;
        private int currPage;

        public int getItemsCount() {
            return itemsCount;
        }

        public void setItemsCount(int itemsCount) {
            this.itemsCount = itemsCount;
        }

        public int getPagesCount() {
            return pagesCount;
        }

        public void setPagesCount(int pagesCount) {
            this.pagesCount = pagesCount;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }
    }

}
