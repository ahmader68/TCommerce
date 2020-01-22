package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HBM on 28/04/2018.
 */

public class InitProg {
    private String apkExpire;
    private String company;
    private String dir;
    private String align;
    private String Currency;
    private String lang;
    private String apkVersion;
    private String CurrModule;
    private String CurrSite;
    private String CurrUser;
    private String apkUrl;

    public String getApkExpire() {
        return apkExpire;
    }

    public void setApkExpire(String apkExpire) {
        this.apkExpire = apkExpire;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getCurrModule() {
        return CurrModule;
    }

    public void setCurrModule(String currModule) {
        CurrModule = currModule;
    }

    public String getCurrSite() {
        return CurrSite;
    }

    public void setCurrSite(String currSite) {
        CurrSite = currSite;
    }

    public String getCurrUser() {
        return CurrUser;
    }

    public void setCurrUser(String currUser) {
        CurrUser = currUser;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
