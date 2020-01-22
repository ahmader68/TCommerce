package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Blog_Post.BlogPostFragment;
import com.intechdev.tcommerce.Models.ItemBlog;
import com.intechdev.tcommerce.R;
import com.squareup.picasso.Picasso;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogListHolder> {

    private Context context;
    private ItemBlog itemBlog;

    public BlogListAdapter(Context context, ItemBlog itemBlog) {
        this.context = context;
        this.itemBlog = itemBlog;
    }

    @NonNull
    @Override
    public BlogListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_blog_list,parent,false);

        return new BlogListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogListHolder holder, final int position) {
        String icon = itemBlog.getItems().get(position).getIcon();
        if(!icon.equals("")) {
            Picasso.get().load(itemBlog.getItems().get(position).getIcon()).into(holder.imgBlog);
        }else{
            holder.imgBlog.setImageDrawable(context.getResources().getDrawable(R.drawable.capture1));
        }
        holder.txtTitle.setText(itemBlog.getItems().get(position).getName());
        holder.txtDesc.setText(itemBlog.getItems().get(position).getBrief());

        holder.cvBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",itemBlog.getItems().get(position).getId());
                bundle.putString("title",itemBlog.getItems().get(position).getName());
                bundle.putParcelable("album", (Parcelable) itemBlog.getItems().get(position).getAlbums());
                bundle.putString("url",itemBlog.getItems().get(position).getUrl());
                bundle.putString("desc",itemBlog.getItems().get(position).getBrief());
                Fragment fragment = new BlogPostFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout,fragment);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemBlog.getItems().size();
    }

    class BlogListHolder extends RecyclerView.ViewHolder{


        ImageView imgBlog;
        TextView txtTitle,txtDesc;
        CardView cvBlog;
        public BlogListHolder(@NonNull View itemView) {
            super(itemView);
            imgBlog = itemView.findViewById(R.id.img_rvBlogList_productImage);
            txtTitle = itemView.findViewById(R.id.txt_rvBlogList_title);
            txtDesc = itemView.findViewById(R.id.txt_rvBlogList_desc);
            cvBlog = itemView.findViewById(R.id.cv_rvBlogList_mainCard);
        }
    }
}
