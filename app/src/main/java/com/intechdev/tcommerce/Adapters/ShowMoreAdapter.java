package com.intechdev.tcommerce.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ItemListStock;
import com.intechdev.tcommerce.Models.StockListModel;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Stock_Details.StockDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowMoreAdapter extends RecyclerView.Adapter<ShowMoreAdapter.ShowMoreHolder> {

    private Activity context;
    public StockListModel list;
    public ItemListStock itemList;
    private boolean mode;



    private View viewRectangle , viewSquare;

    public ShowMoreAdapter(Activity context, StockListModel list, boolean mode) {
        this.context = context;
        this.list = list;
        this.mode = mode;
    }

    public ShowMoreAdapter(Activity context, ItemListStock list, boolean mode) {
        this.context = context;
        this.itemList = list;
        this.mode = mode;
    }

    public List<StockListModel.Product> getList() {
        if (list != null) {
            return list.getItems();
        }
        return null;
    }

    public List<ItemListStock.Items> getItemList() {
        if (itemList != null){
            return itemList.getItems();
        }
        return null;
    }

    @NonNull
    @Override
    public ShowMoreAdapter.ShowMoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * mode = true => view = rectangle
         * mode = false => view = square
         */
        if (mode) {
            viewRectangle = LayoutInflater.from(context).inflate(R.layout.rv_stocklist_product , parent, false);
            return new ShowMoreHolder(viewRectangle);
        } else {
            viewSquare = LayoutInflater.from(context).inflate(R.layout.rv_stocklist_product_square , parent, false);
            return new ShowMoreHolder(viewSquare);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ShowMoreHolder holder, final int position) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int w = (width/2) - 40;

        if (!mode){
            holder.cvSquareList.setMinimumHeight(w);
            holder.cvSquareList.setMinimumWidth(w);
        }

        if (list != null) {
            StockListModel.Product product = list.getItems().get(position);
            Picasso.get().load(product.getIcon()).into(holder.img_rvStockList_product);
            holder.txt_rvStockList_title.setText(product.getName());
            if (mode) {
                holder.txt_rvStockList_desc.setText(product.getStockDescription());
            }
            holder.txt_rvStockList_price.setText(product.getPrice());
            if (product.getPriceOffer() != null) {
                holder.txt_rvStockList_priceOff.setText(product.getPriceOffer());
            }
        } else if (itemList != null) {
            ItemListStock.Items items = itemList.getItems().get(position);
            Picasso.get().load(items.getIcon()).into(holder.img_rvStockList_product);
            holder.txt_rvStockList_title.setText(items.getName());
            if (mode) {
                holder.txt_rvStockList_desc.setText(items.getDescription());
            }
            holder.txt_rvStockList_price.setText(items.getPrice());
            if (items.getBuyPrice() != null) {
                holder.txt_rvStockList_priceOff.setText(items.getBuyPrice());
            }
        }
        if(!mode) {
            holder.cvSquareList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id = list.getItems().get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putLong("productId",id);
                    Fragment fragment = new StockDetailsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_mainFragment_mainLayout,fragment);
                    transaction.commit();
                }
            });
        }else {

            holder.cvRectangleList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id = list.getItems().get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putLong("productId",id);
                    Fragment fragment = new StockDetailsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_mainFragment_mainLayout,fragment);
                    transaction.commit();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.getItems().size();
        } else if (itemList != null){
            return itemList.getItems().size();
        }
        return 0;
    }

    class ShowMoreHolder extends RecyclerView.ViewHolder {

        ImageView img_rvStockList_product;
        TextView txt_rvStockList_title,
                txt_rvStockList_desc,
                txt_rvStockList_price,
                txt_rvStockList_priceOff;

        CardView cvSquareList,cvRectangleList;

        private ShowMoreHolder(@NonNull View itemView) {
            super(itemView);

            img_rvStockList_product = itemView.findViewById(R.id.img_rvStockList_product);
            txt_rvStockList_title = itemView.findViewById(R.id.txt_rvStockList_title);
            txt_rvStockList_desc = itemView.findViewById(R.id.txt_rvStockList_desc);
            txt_rvStockList_price = itemView.findViewById(R.id.txt_rvStockList_price);
            txt_rvStockList_priceOff = itemView.findViewById(R.id.txt_rvStockList_priceOff);
            cvRectangleList = itemView.findViewById(R.id.cv_rvStockListProduct_cardView);
            cvSquareList = itemView.findViewById(R.id.cv_rvStockListProductSquare_cardView);
        }
    }
}
