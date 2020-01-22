package com.intechdev.tcommerce.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intechdev.tcommerce.Classes.Network;
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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements TCommerceDataSource {

    private static final String BASE_URL = "http://tc.intechdev.com/";
    //private static final String BASE_URL = "http://tc.intechserver.com/";

    private ApiService apiService;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    ServerDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(Network.getOkHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService =retrofit.create(ApiService.class);
    }


    @Override
    public Single<Login> login(Login login) {
        return apiService.loginService(login.getUsr(),login.getPwd());
    }

    @Override
    public Single<Login> register(Login login) {
        return apiService.registerService(login.getUsr(),login.getPwd(),login.getPwd(),login.getMobile(),login.getEmail());
    }

    @Override
    public Single<StockListModel> getListStockNew(/*String usr, String pwd*/) {
        return apiService.getListStockNew();
    }

    @Override
    public Single<StockListModel> getHitsProducts() {
        return apiService.getListStockBestView();
    }

    @Override
    public Single<StockListModel> getBestSellsProduct() {
        return apiService.getListStockBestSale();
    }

    @Override
    public Single<StockListModel> getListStockBestView() {
        return apiService.getListStockBestView();
    }

    @Override
    public Single<ItemListStock> getListStock(String categoryId, String subCat, String sort) {
        return apiService.getListStock(categoryId , subCat , sort);
    }

    @Override
    public Single<ItemAdvanceSearch> getAdvanceSearchItem(int prodId) {
        return apiService.getAdvanceSearchItem(prodId);
    }

    @Override
    public Single<ItemImageSlider> getSlideImage() {
        return apiService.getSlideImage();
    }

    @Override
    public Single<Login> editUser(Login login) {
        return apiService.editProfile(login.getEmail(),login.getMobile(),login.getUsr(),login.getUsrId());
    }

    @Override
    public Single<Login> changePassword(Login login) {
        return apiService.changePass(login.getUsrId(),login.getPwd(),login.getNewpwd());
    }

    @Override
    public Single<Result> addAddress(ItemAddress itemAddress) {
        return apiService.addAddress(itemAddress.getItems().get(0).getUsrid(),itemAddress.getItems().get(0).getAddressTxt(),itemAddress.getItems().get(0).getPostalTxt(),itemAddress.getItems().get(0).getAddressTitle(),itemAddress.getItems().get(0).getCity(),itemAddress.getItems().get(0).getProvince(),itemAddress.getItems().get(0).getTelTxt());
    }


    @Override
    public Single<ItemAddress> getAddresses(int userid) {
        return apiService.getListAddress(userid);
    }

    @Override
    public Single<Result> editAddress(ItemAddress itemAddress) {
        return apiService.editAddress(itemAddress.getItems().get(0).getUsrid(),itemAddress.getItems().get(0).getId(),itemAddress.getItems().get(0).getAddressTxt(),itemAddress.getItems().get(0).getPostalTxt(),itemAddress.getItems().get(0).getAddressTitle(),itemAddress.getItems().get(0).getCity(),itemAddress.getItems().get(0).getProvince(),itemAddress.getItems().get(0).getTelTxt());
    }

    @Override
    public Single<ItemOrders> getOrderHistory(int userId) {
        return apiService.getOrders(userId);
    }

    @Override
    public Single<ItemTransactions> getTransactions(int userId) {
        return apiService.getTransactions(userId);
    }

    @Override
    public Single<ItemDiscounts> getDiscounts(int userId) {
        return apiService.getDiscount(userId);
    }

    @Override
    public Single<ItemComments> getComments(int userId, int relId) {
        return apiService.getListComment(userId,relId);
    }

    @Override
    public Single<ItemListStock> getListStock(Map<String, String> options) {
        return apiService.getListStock(options);
    }

    @Override
    public Single<Result> deleteFromBasket(int usrid, int OrderId) {
        return apiService.DelFromBasket(usrid,OrderId);
    }

    @Override
    public Single<ItemOrdersBasket> showOrderInBasket(int usrid) {
        return apiService.showOrdersBasket(usrid);
    }

    @Override
    public Single<ItemStockDetails.ItemStockDetailsObject> getStockDetails(long id) {
        return apiService.getStockDetails(id);
    }

    @Override
    public Single<Result> addToBasket(int ProdId, int OrderCount,int usrid) {
        return apiService.addToBasket(ProdId,OrderCount,usrid);
    }

    @Override
    public Single<Result> deleteAddress(int usrid, int addressId) {
        return apiService.deleteAddress(usrid, addressId);
    }

    @Override
    public Single<ItemBlog> getListBlog(int usrid) {
        return apiService.getListBlog(usrid);
    }

    @Override
    public Single<BlogPost> getBlogPost(int usrid, int id) {
        return apiService.getBlogPost(usrid, id);
    }

    @Override
    public Single<Result> addRate(int prodId,int rate) {
        return apiService.addRating(prodId, rate);
    }

    @Override
    public Single<Result> addToFav(int userid, int itemId) {
        return apiService.addToFavorite(userid, itemId);
    }

    @Override
    public Single<Result> deleteFav(int userid, int itemId) {
        return apiService.deleteFavorite(userid, itemId);
    }

    @Override
    public Single<Favourite> getFavourite(int userId) {
        return apiService.getFavorites(userId);
    }

    @Override
    public Single<InitProg> getInit() {
        return apiService.getInitApplication();
    }

    @Override
    public Single<ItemGroupStock> getGroupStock() {
        return apiService.getGroupStock();
    }

    @Override
    public Single<ItemListStock> getListStock(String categoryId, String sort) {
        return apiService.getListStock(categoryId , sort);
    }


}
