package com.intechdev.tcommerce.Blog_List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.BlogListAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemBlog;
import com.intechdev.tcommerce.R;

public class BlogListFragment extends BaseFragment implements BlogListContract.View {

    private BlogListContract.Presenter presenter;

    private BlogListAdapter blogListAdapter;

    private RecyclerView rvBlogList;

    private SharedPreferences sharedPreferences;

    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogListPresenter(new TCommerceRepository());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userId",0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_blog_list;
    }

    @Override
    public void setupViews() {
        rvBlogList = rootView.findViewById(R.id.rv_blogListFragment_mainList);
        presenter.getBlogList(userId);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
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
    public void getBlogList(ItemBlog itemBlog) {
        blogListAdapter = new BlogListAdapter(getViewContext(),itemBlog);
        rvBlogList.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvBlogList.setAdapter(blogListAdapter);
    }
}
