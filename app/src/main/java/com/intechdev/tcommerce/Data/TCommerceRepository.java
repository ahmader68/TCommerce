package com.intechdev.tcommerce.Data;

import com.intechdev.tcommerce.Models.BlogPost;
import com.intechdev.tcommerce.Models.Favourite;
import com.intechdev.tcommerce.Models.InitProg;
import com.intechdev.tcommerce.Models.ItemAddress;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;
import com.intechdev.tcommerce.Models.ItemBlog;
import com.intechdev.tcommerce.Models.ItemComments;
import com.intechdev.tcommerce.Models.ItemDiscounts;
import com.intechdev.tcommerce.Models.ItemGroupStock;
import com.intechdev.tcommerce.Models.ItemImageSlider;
import com.intechdev.tcommerce.Models.ItemListStock;
import com.intechdev.tcommerce.Models.ItemOrders;
import com.intechdev.tcommerce.Models.ItemOrdersBasket;
import com.intechdev.tcommerce.Models.ItemStockDetails;
import com.intechdev.tcommerce.Models.ItemTransactions;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.Models.ProductList;
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.Models.StockListModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class TCommerceRepository implements TCommerceDataSource {

    private ServerDataSource serverDataSource = new ServerDataSource();

    @Override
    public Single<Login> login(Login login) {
        return serverDataSource.login(login);
    }

    @Override
    public Single<Login> register(Login login) {
        return serverDataSource.register(login);
    }

    @Override
    public Single<StockListModel> getListStockNew(/*String usr, String pwd*/) {
        return serverDataSource.getListStockNew();
    }

    @Override
    public Single<StockListModel> getHitsProducts() {
        return serverDataSource.getHitsProducts();
    }

    @Override
    public Single<StockListModel> getBestSellsProduct() {
        return serverDataSource.getBestSellsProduct();
    }

    @Override
    public Single<StockListModel> getListStockBestView() {
        return serverDataSource.getListStockBestView();
    }

    @Override
    public Single<ItemListStock> getListStock(String categoryId, String subCat, String sort) {
        return serverDataSource.getListStock(categoryId , subCat , sort);
    }

    @Override
    public Single<ItemAdvanceSearch> getAdvanceSearchItem(int prodId) {
        return serverDataSource.getAdvanceSearchItem(prodId);
    }

    @Override
    public Single<ItemImageSlider> getSlideImage() {
        return serverDataSource.getSlideImage();
    }

    @Override
    public Single<Login> editUser(Login login) {
        return serverDataSource.editUser(login);
    }

    @Override
    public Single<Login> changePassword(Login login) {
        return serverDataSource.changePassword(login);
    }

    @Override
    public Single<Result> addAddress(ItemAddress itemAddress) {
        return serverDataSource.addAddress(itemAddress);
    }


    @Override
    public Single<ItemAddress> getAddresses(int userid) {
        return serverDataSource.getAddresses(userid);
    }

    @Override
    public Single<Result> editAddress(ItemAddress itemAddress) {
        return serverDataSource.editAddress(itemAddress);
    }

    @Override
    public Single<ItemOrders> getOrderHistory(int userId) {
        return serverDataSource.getOrderHistory(userId);
    }

    @Override
    public Single<ItemTransactions> getTransactions(int userId) {
        return serverDataSource.getTransactions(userId);
    }

    @Override
    public Single<ItemDiscounts> getDiscounts(int userId) {
        return serverDataSource.getDiscounts(userId);
    }

    @Override
    public Single<ItemComments> getComments(int userId, int relId) {
        return serverDataSource.getComments(userId, relId);
    }

    @Override
    public Single<Result> deleteFromBasket(int usrid, int OrderId) {
        return serverDataSource.deleteFromBasket(usrid, OrderId);
    }

    @Override
    public Single<ItemOrdersBasket> showOrderInBasket(int usrid) {
        return serverDataSource.showOrderInBasket(usrid);
    }

    @Override
    public Single<ItemStockDetails.ItemStockDetailsObject> getStockDetails(long id) {
        return serverDataSource.getStockDetails(id);
    }

    @Override
    public Single<Result> addToBasket(int ProdId, int OrderCount, int usrid) {
        return serverDataSource.addToBasket(ProdId, OrderCount, usrid);
    }

    @Override
    public Single<ItemListStock> getListStock(Map<String, String> options) {
        return serverDataSource.getListStock(options);
    }

    @Override
    public Single<ItemListStock> getListStock(String categoryId, String sort) {
        return serverDataSource.getListStock(categoryId, sort);
    }

    @Override
    public Single<Result> deleteAddress(int usrid, int addressId) {
        return serverDataSource.deleteAddress(usrid, addressId);
    }

    @Override
    public Single<ItemGroupStock> getGroupStock() {
        return serverDataSource.getGroupStock();
    }

    @Override
    public Single<ItemBlog> getListBlog(int usrid) {
        return serverDataSource.getListBlog(usrid);
    }

    @Override
    public Single<BlogPost> getBlogPost(int usrid, int id) {
        return serverDataSource.getBlogPost(usrid, id);
    }

    @Override
    public Single<Result> addRate(int prodId, int rate) {
        return serverDataSource.addRate(prodId, rate);
    }

    @Override
    public Single<Result> addToFav(int userid, int itemId) {
        return serverDataSource.addToFav(userid, itemId);
    }

    @Override
    public Single<Result> deleteFav(int userid, int itemId) {
        return serverDataSource.deleteFav(userid, itemId);
    }

    @Override
    public Single<Favourite> getFavourite(int userId) {
        return serverDataSource.getFavourite(userId);
    }

    @Override
    public Single<InitProg> getInit() {
        return serverDataSource.getInit();
    }


}
