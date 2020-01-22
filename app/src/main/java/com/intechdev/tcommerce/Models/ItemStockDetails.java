package com.intechdev.tcommerce.Models;



import java.util.List;

/**
 * Created by HBM on 22/06/2018.
 */

public class ItemStockDetails {
    private int id;
    private String name;
    private int price;
    private int rate;
    private String Code;
    private String ver;
    private String icon;
    private String createDate;
    private int catId;
    private int scatId;
    private String catName;
    private String scatName;
    private int cmdCount;
    private String quantity;
    private int visitCount;
    private int dnlCount;
    private String Attributes;
    private String tags;
    private String brief;
    private String body;
    private String nav;
    private List<ItemStockProperies> AttribArray;
    private List<ItemStockAlbum> album;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
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

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getDnlCount() {
        return dnlCount;
    }

    public void setDnlCount(int dnlCount) {
        this.dnlCount = dnlCount;
    }

    public String getAttributes() {
        return Attributes;
    }

    public void setAttributes(String attributes) {
        Attributes = attributes;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public List<ItemStockProperies> getAttribArray() {
        return AttribArray;
    }

    public void setAttribArray(List<ItemStockProperies> attribArray) {
        AttribArray = attribArray;
    }

    public List<ItemStockAlbum> getAlbum() {
        return album;
    }

    public void setAlbum(List<ItemStockAlbum> album) {
        this.album = album;
    }

    public class ItemStockAlbum{
        private int id;
        private String name;
        private String brief;
        private String icon;
        private String mainImage;

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
    }

    public class ItemStockProperies{
        private String title;
        private String value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class ItemStockDetailsObject
    {
        private Settings settings;
        private List<ItemStockDetails> items;

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemStockDetails> getItems() {
            return items;
        }

        public void setItems(List<ItemStockDetails> items) {
            this.items = items;
        }
    }

    public class ItemStockDetailsResponse {
        private int page;
        private List<ItemStockDetailsObject> results;
        private int totalResults;
        private int totalPages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ItemStockDetailsObject> getResults() {
            return results;
        }

        public void setResults(List<ItemStockDetailsObject> results) {
            this.results = results;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

}
