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
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.Models.StockListModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

import retrofit2.http.GET;

import retrofit2.http.Query;

public interface TCommerceDataSource {
    Single<Login> login(Login login);
    Single<Login> register(Login login);
    Single<StockListModel> getListStockNew(/*String usr, String pwd*/);
    Single<StockListModel> getHitsProducts();
    Single<StockListModel> getBestSellsProduct();
    Single<StockListModel> getListStockBestView();
    Single<ItemListStock> getListStock(String categoryId,String subCat,String sort);
    Single<ItemAdvanceSearch> getAdvanceSearchItem(int prodId);
    Single<ItemImageSlider> getSlideImage();
    Single<Login> editUser(Login login);
    Single<Login> changePassword(Login login);
    Single<Result> addAddress(ItemAddress itemAddress);
    Single<ItemAddress> getAddresses(int userid);
    Single<Result> editAddress(ItemAddress itemAddress);
    Single<ItemOrders> getOrderHistory(int userId);
    Single<ItemTransactions> getTransactions(int userId);
    Single<ItemDiscounts> getDiscounts(int userId);
    Single<ItemComments> getComments(int userId,int relId);
    Single<ItemListStock> getListStock(Map<String, String> options);
    Single<Result> deleteFromBasket(int usrid,int OrderId);
    Single<ItemOrdersBasket> showOrderInBasket(int usrid);
    Single<ItemStockDetails.ItemStockDetailsObject> getStockDetails(long id);
    Single<Result> addToBasket(int ProdId,int OrderCount,int usrid);
    Single<Result> deleteAddress(int usrid,int addressId);
    Single<ItemBlog> getListBlog(int usrid);
    Single<BlogPost> getBlogPost(int usrid,int id);
    Single<Result> addRate(int prodId,int rate);
    Single<Result> addToFav(int userid,int itemId);
    Single<Result> deleteFav(int userid,int itemId);
    Single<Favourite> getFavourite(int userId);
    Single<InitProg> getInit();
    Single<ItemGroupStock> getGroupStock();
    Single<ItemListStock> getListStock(String categoryId , String sort);
}
