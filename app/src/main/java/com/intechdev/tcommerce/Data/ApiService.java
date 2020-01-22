package com.intechdev.tcommerce.Data;

import com.intechdev.tcommerce.Models.Aboutus;
import com.intechdev.tcommerce.Models.BlogPost;
import com.intechdev.tcommerce.Models.Favourite;
import com.intechdev.tcommerce.Models.InitProg;
import com.intechdev.tcommerce.Models.ItemAddress;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;
import com.intechdev.tcommerce.Models.ItemBlog;
import com.intechdev.tcommerce.Models.ItemComments;
import com.intechdev.tcommerce.Models.ItemDiscounts;
import com.intechdev.tcommerce.Models.ItemFavorites;
import com.intechdev.tcommerce.Models.ItemGroupStock;
import com.intechdev.tcommerce.Models.ItemImageSlider;
import com.intechdev.tcommerce.Models.ItemListStock;
import com.intechdev.tcommerce.Models.ItemOrders;
import com.intechdev.tcommerce.Models.ItemOrdersBasket;
import com.intechdev.tcommerce.Models.ItemPostTypes;
import com.intechdev.tcommerce.Models.ItemProdAttribs;
import com.intechdev.tcommerce.Models.ItemRefreshProdAttribsPrice;
import com.intechdev.tcommerce.Models.ItemStockDetails;
import com.intechdev.tcommerce.Models.ItemTransactions;
import com.intechdev.tcommerce.Models.Login;
import com.intechdev.tcommerce.Models.ProductList;
import com.intechdev.tcommerce.Models.Result;
import com.intechdev.tcommerce.Models.StockListModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("wservice/default.aspx?native=1&Action=Init")
    Single<InitProg> getInitApplication();

    @FormUrlEncoded
    @POST("wservice/default.aspx?native=1&Action=Login")
    Single<Login> loginService(@Field("usr") String usr, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("wservice/default.aspx?native=1&Action=Register")
    Single<Login> registerService(@Field("usr") String usr,
                                  @Field("pwd") String pwd,
                                  @Field("pwd2") String pwd2,
                                  @Field("mobile") String mobile,
                                  @Field("email") String email);

    @GET("wservice/default.aspx?native=1&Action=Items&module=Link&type=Slide&showimage=2")
    Single<ItemImageSlider> getSlideImage();

    @GET("wservice/default.aspx?Action=Detail&module=SmpCnt&type=aboutus&native=1")
    Single<Aboutus.AboutusObject> getAboutus();

    @GET("wservice/default.aspx?Action=Items&module=CmsCat&type=Products&native=1&showimage=2")
    Single<ItemGroupStock> getGroupStock();

    @GET("wservice/default.aspx?native=1&Action=Items&module=Prod&type=Products&showimage=2")
    Single<ItemListStock> getListStock(@Query("cat") String categoryId,
                                       @Query("scat") String subCat,
                                       @Query("sort") String sort);

    @GET("wservice/default.aspx?native=1&Action=Items&module=Prod&type=Products&showimage=2")
    Single<ItemListStock> getListStock(@QueryMap Map<String, String> options);

    @GET("wservice/default.aspx?native=1&Action=Items&module=Prod&type=Products&showimage=2")
    Single<ItemListStock> getListStock(@Query("cat") String categoryId,
                                       @Query("sort") String sort);

//    @GET("wservice/default.aspx?native=1&Action=Items&module=CmsCat&type=Products")
//    Single<Login> getGroupStockAndSubGroup(@Query("usr") String usr, @Query("pwd") String pwd);

    //@GET("wservice/default.aspx?native=1&Action=Detail&module=Prod&type=Products&Id=312&showimage=0")

    @FormUrlEncoded
    @POST("wservice/default.aspx?native=1&Action=Detail&module=Prod&type=Products&showimage=2")
    Single<ItemStockDetails.ItemStockDetailsObject> getStockDetails(@Field("Id") long id);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=1&native=1&showimage=2")
    Single<StockListModel> getListStockNew(/*@Query("usr") String usr, @Query("pwd") String pwd*/);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=5&native=1&showimage=2")
    Single<StockListModel> getListStockBestSale(/*@Query("usr") String usr, @Query("pwd") String pwd*/);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=2&native=1&showimage=2")
    Single<StockListModel> getListStockBestView(/*@Query("usr") String usr, @Query("pwd") String pwd*/);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=4&native=1&showimage=2")
    Single<ItemListStock> getListStockSpesial(@Query("usr") String usr, @Query("pwd") String pwd);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=EditUser&native=1")
    Single<Login> editProfile(@Field("email") String email,
                              @Field("mobile") String mobile,
                              @Field("usr") String usr,
                              @Field("usrid") int userId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=EditUser&native=1")
    Single<Login> changePass(@Field("usrid") int userId,
                             @Field("pwd") String pwd,
                             @Field("newpwd") String newpwd);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=GetAddress&native=1")
    Single<ItemAddress> getListAddress(@Field("usrid") int userId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=AddAddress&native=1")
    Single<Result> addAddress(@Field("usrid") int userId,
                              @Field("AddressTxt") String addressTxt,
                              @Field("PostalTxt") String postalTxt,
                              @Field("AddressTitle") String addressTitle,
                              @Field("City") String city,
                              @Field("Province") String province,
                              @Field("telTxt") String telTxt);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=DeleteAddress&native=1")
    Single<Result> deleteAddress(@Field("usrid") int userId,
                                 @Field("AddressId") int addressId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=UpdateAddress&native=1")
    Single<Result> editAddress(@Field("usrid") int userId,
                               @Field("AddressId") int addressId,
                               @Field("AddressTxt") String addressTxt,
                               @Field("PostalTxt") String postalTxt,
                               @Field("AddressTitle") String addressTitle,
                               @Field("City") String city,
                               @Field("Province") String province,
                               @Field("telTxt") String telTxt);


    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=AddToFav&native=1&module=Module&type=AddToFav")
    Single<Result> addToFavorite(@Field("usrid") int userId,
                                 @Field("ItemId") int itemId);


    @GET("wservice/default.aspx?Action=AddToFav&retId=1&&module=Module&type=InformMe")
    Single<Result> addInformMe(@Query("usrid") String userId,
                               @Query("ItemId") String itemId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=DelFromFav&native=1")
    Single<Result> deleteFavorite(@Field("usrid") int userId,
                                  @Field("ItemId") int itemId);


    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=GetComments&withContent=true&native=1")
    Single<ItemComments> getListComment(@Field("usrid") int userId,
                                        @Field("relid") int relid);

    @GET("wservice/default.aspx?Action=SaveComment&native=1")
    Single<Result> addComment(@Query("usrid") String userId,
                              @Query("relid") String relid,
                              @Query("cmd") String cmd,
                              @Query("Alias") String alias,
                              @Query("Subject") String subject,
                              @Query("Email") String email,
                              @Query("WebPage") String webPage);

    @GET("wservice/default.aspx?Action=Deletecomment&native=1")
    Single<Result> deleteComment(@Query("usrid") String userId,
                                 @Query("CId") String addressId);

    @GET("wservice/default.aspx?Action=GetMyFav&native=1")
    Single<Favourite> getFavorites(@Query("usrid") int userId);


    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=GetOrders&native=1")
    Single<ItemOrders> getOrders(@Field("usrid") int userId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=GetMyTransaction&native=1")
    Single<ItemTransactions> getTransactions(@Query("usrid") int userId);

//    @GET("wservice/default.aspx?Action=GetComments&withContent=true&native=1")
//    Single<ItemComments.ItemCommentsObject> getListComment(@Query("usrid") String userId);


    @GET("wservice/default.aspx?Action=ShowProdAttribs&native=1")
    Single<ItemProdAttribs.ItemProdAttribsObject> getShowProdAttribs(@Query("usrid") String userId, @Query("ProdId") String prodId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=AddRate&native=1")
    Single<Result> addRating(@Field("relid") int prodId, @Field("rate") int rate);

    @GET("wservice/default.aspx?Action=GetPostTypes&native=1")
    Single<ItemPostTypes.ItemPostTypesObject> getPostTypes(@Query("relid") String prodId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=GetDiscount&native=1")
    Single<ItemDiscounts> getDiscount(@Field("usrid") int userId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=Items&module=AdvCnt&type=Article&&showimage=0&native=1")
    Single<ItemBlog> getListBlog(@Field("usrid") int userId);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=Detail&module=AdvCnt&&type=News&native=1")
    Single<BlogPost> getBlogPost(@Field("usrid") int userId,
                                 @Field("Id") int id);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=AddToBasket&native=1")
    Single<Result> addToBasket(@Field("ProdId") int ProdId,
                               @Field("OrderCount") int OrderCount,
                               @Field("usrid") int userId);

//    @FormUrlEncoded
//    @POST("wservice/default.aspx?Action=AddToBasket&native=1")
//    Single<Result> addToBasket(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=ShowOrdersBasket&native=1")
    Single<ItemOrdersBasket> showOrdersBasket(@Field("usrid") int userId);


    @FormUrlEncoded
    @POST("wservice/default.aspx?Action=DelFromBasket&native=1")
    Single<Result> DelFromBasket(@Field("usrid") int userId,
                                 @Field("OrderId") int OrderId);

    @GET("wservice/default.aspx?Action=Items&module=CmsCat&type=ProductAttributes&showimage=0&native=1")
    Single<ItemAdvanceSearch> getAdvanceSearchItem(@Query("ProdId") int prodId);

    @GET("wservice/default.aspx?Action=RefreshProdAttribsPrice&native=1")
    Single<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject> refreshProdAttribsPrice(@QueryMap Map<String, String> options);

    @GET("wservice/default.aspx?Action=SubmitBasket&native=1")
    Single<Result> submitBasket(@Query("usrid") String userId,
                                @Query("AddressId") String addressId,
                                @Query("posttype") String posttype,
                                @Query("paytype") String paytype);

    @GET("wservice/default.aspx?Action=SubmitBasket&native=1&paytype=Fish&ReceiptNumber=123456789&PayDate=1397/05/05&CardInfo=123456789")
    Single<Result> submitBasket2(@Query("usr") String userId,
                                 @Query("AddressId") String addressId,
                                 @Query("posttype") String posttype);

    @GET("wservice/default.aspx?native=1&Action=AddAppLog")
    Single<Result> addAppLogService(@Query("WS_Method") String wsMethod,
                                    @Query("Requst_Data") String requestData,
                                    @Query("Result_Data") String resultData,
                                    @Query("UserId") int userId,
                                    @Query("Description") String description);

//    @GET("&Action=Init")158326349
//    Single<InitProrgResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
//    @GET("&Action=Init")
//    Single<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
//
//    @GET("&Action=Init")
//    Single<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
