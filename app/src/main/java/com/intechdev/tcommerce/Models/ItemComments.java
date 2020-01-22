package com.intechdev.tcommerce.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/07/2018.
 */

public class ItemComments {

    private List<SubItemComments> items;

    public List<SubItemComments> getItems() {
        return items;
    }

    public void setItems(List<SubItemComments> items) {
        this.items = items;
    }

    public static class SubItemComments{
        private String senderName;

        private int senderId;

        private String sendDate;

        private String alias;

        private String subject;

        private String tel;

        private String WebPage;

        private String PositiveRank;

        private String NegativeRank;

        private String IPAddress;

        private String Language;

        private String Unread;

        private String Status;

        private String email;

        private String Content;

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public int getSenderId() {
            return senderId;
        }

        public void setSenderId(int senderId) {
            this.senderId = senderId;
        }

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getWebPage() {
            return WebPage;
        }

        public void setWebPage(String webPage) {
            WebPage = webPage;
        }

        public String getPositiveRank() {
            return PositiveRank;
        }

        public void setPositiveRank(String positiveRank) {
            PositiveRank = positiveRank;
        }

        public String getNegativeRank() {
            return NegativeRank;
        }

        public void setNegativeRank(String negativeRank) {
            NegativeRank = negativeRank;
        }

        public String getIPAddress() {
            return IPAddress;
        }

        public void setIPAddress(String IPAddress) {
            this.IPAddress = IPAddress;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String language) {
            Language = language;
        }

        public String getUnread() {
            return Unread;
        }

        public void setUnread(String unread) {
            Unread = unread;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }
    }


}
