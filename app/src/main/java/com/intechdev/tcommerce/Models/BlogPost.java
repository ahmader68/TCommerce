package com.intechdev.tcommerce.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/08/2018.
 */

public class BlogPost {

    private Setting settings;
    private List<SubBlogPost> items;

    public Setting getSettings() {
        return settings;
    }

    public void setSettings(Setting settings) {
        this.settings = settings;
    }

    public List<SubBlogPost> getItems() {
        return items;
    }

    public void setItems(List<SubBlogPost> items) {
        this.items = items;
    }

    public class SubBlogPost{
        private int id;
        private String name;
        private float rate;
        private String icon;
        private String url;
        private String createDate;
        private String publishDate;
        private String archiveDate;
        private String publishDateEN;
        private int catId;
        private int isArchive;
        private int scatId;
        private String catName;
        private String scatName;
        private int cmdCount;
        private int visitCount;
        private String reference;
        private String referenceLink;
        private String tags;
        private String brief;
        private List<Album> album;
        private String nav;
        private String fileName;
        private String fileExt;
        private String attachLink;
        private String attahIcon;
        private String attachSize;
        private String fileaddress;
        private String body;

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

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
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

        public String getPublishDateEN() {
            return publishDateEN;
        }

        public void setPublishDateEN(String publishDateEN) {
            this.publishDateEN = publishDateEN;
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

        public List<Album> getAlbum() {
            return album;
        }

        public void setAlbum(List<Album> album) {
            this.album = album;
        }

        public String getNav() {
            return nav;
        }

        public void setNav(String nav) {
            this.nav = nav;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileExt() {
            return fileExt;
        }

        public void setFileExt(String fileExt) {
            this.fileExt = fileExt;
        }

        public String getAttachLink() {
            return attachLink;
        }

        public void setAttachLink(String attachLink) {
            this.attachLink = attachLink;
        }

        public String getAttahIcon() {
            return attahIcon;
        }

        public void setAttahIcon(String attahIcon) {
            this.attahIcon = attahIcon;
        }

        public String getAttachSize() {
            return attachSize;
        }

        public void setAttachSize(String attachSize) {
            this.attachSize = attachSize;
        }

        public String getFileaddress() {
            return fileaddress;
        }

        public void setFileaddress(String fileaddress) {
            this.fileaddress = fileaddress;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
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

    public class Setting{
        private int itemsCount;

        public int getItemsCount() {
            return itemsCount;
        }

        public void setItemsCount(int itemsCount) {
            this.itemsCount = itemsCount;
        }
    }
}
