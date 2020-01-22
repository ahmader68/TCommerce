package com.intechdev.tcommerce.Blog_Post;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.example.sliderviewlibrary.SliderView;
import com.google.android.material.slider.Slider;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.BlogPost;
import com.intechdev.tcommerce.Models.ItemBlog;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import in.myinnos.imagesliderwithswipeslibrary.SliderLayout;
import in.myinnos.imagesliderwithswipeslibrary.SliderTypes.TextSliderView;

public class BlogPostFragment extends BaseFragment implements BlogPostContract.View {

    private BlogPostContract.Presenter presenter;
    private SliderView sliderView;
    private TextView txtContext;
    private SimpleRatingBar ratingBar;
    private ImageView imgShare;
    private WebView wvBlog;

    private Bundle bundle;

    private List<ItemBlog.Album> albums;

    private String title,desc,url;

    private SharedPreferences sharedPreferences;

    private int id,userId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        id = bundle.getInt("id");
        albums = bundle.getParcelable("album");
        title = bundle.getString("title");
        desc = bundle.getString("desc");
        url = bundle.getString("url");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userId",0);
        presenter = new BlogPostPresenter(new TCommerceRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_blog_post;
    }

    @Override
    public void setupViews() {
        txtContext = rootView.findViewById(R.id.txt_blogPostFragment_contextTitle);
        ratingBar = rootView.findViewById(R.id.rate_blogPostFragment_rate);
        imgShare = rootView.findViewById(R.id.imgs_blogPostFragment_share);
        wvBlog = rootView.findViewById(R.id.wb_blogPostFragment_blog);
        sliderView = rootView.findViewById(R.id.imgs_blogPostFragment_imageSlider);

        TimerTask task = sliderView.getTimerTask();
        Timer timer = new Timer();
        timer.schedule(task,5000,2000);
        ArrayList<String> urls = new ArrayList<>();
        if(albums != null && albums.size() > 0 ) {
            for (int i = 0; i < albums.size(); i++) {
                urls.add(albums.get(i).getIcon());
            }
            sliderView.setUrls(urls);
        }else{
            ArrayList<Integer> images = new ArrayList<>();
            images.add(R.drawable.capture1);
            sliderView.setImages(images);

        }

        presenter.getBlogPost(userId,id);

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void getBlogPost(BlogPost blogPost) {
        wvBlog.getSettings().setJavaScriptEnabled(true);
        wvBlog.getSettings().setLoadWithOverviewMode(true);
        wvBlog.getSettings().setUseWideViewPort(true);
        wvBlog.getSettings().setBuiltInZoomControls(false);
        wvBlog.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvBlog.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        wvBlog.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wvBlog.getSettings().setAppCacheEnabled(false);
        wvBlog.getSettings().setBlockNetworkImage(true);
        wvBlog.getSettings().setLoadsImagesAutomatically(true);
        wvBlog.getSettings().setGeolocationEnabled(false);
        wvBlog.getSettings().setNeedInitialFocus(false);
        wvBlog.getSettings().setSaveFormData(false);
        wvBlog.getSettings().setDefaultFontSize(40);

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = blogPost.getItems().get(0).getBody();

        wvBlog.loadDataWithBaseURL("", html, mimeType, encoding, "");
        wvBlog.getSettings().setDefaultFontSize(40);

        ratingBar.setRating(blogPost.getItems().get(0).getRate());
    }
}
