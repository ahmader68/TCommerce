package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Favourites.FavouritesContract;
import com.intechdev.tcommerce.Models.Favourite;
import com.intechdev.tcommerce.R;
import com.squareup.picasso.Picasso;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder>{
    private Context context;
    private Favourite favourite;
    private FavouritesContract.View view;
    private int userId, favId;

    public FavouriteAdapter(Context context, Favourite favourite,FavouritesContract.View view,int userId) {
        this.context = context;
        this.favourite = favourite;
        this.view = view;
        this.userId = userId;
    }

    @NonNull
    @Override
    public FavouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_stocklist_product,parent,false);
        return new FavouriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteHolder holder, final int position) {

        favId = favourite.getItems().get(position).getFavId();
        Picasso.get().load(favourite.getItems().get(position).getIcon()).into(holder.imgProd);
        holder.txtPrice.setText(favourite.getItems().get(position).getPrice());
        holder.txtTitle.setText(favourite.getItems().get(position).getProductName());
        holder.txtRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteFav(userId, favId);
            }
        });


    }

    @Override
    public int getItemCount() {
        return favourite.getItems().size();
    }

    class FavouriteHolder extends RecyclerView.ViewHolder{

        ImageView imgProd;
        TextView
                txtTitle,
                txtDesc,
                txtPrice,
                txtTitlePrice,
                txtCurrency,
                txtRemove;

        public FavouriteHolder(@NonNull View itemView) {
            super(itemView);
            imgProd = itemView.findViewById(R.id.img_rvStockList_product);
            txtTitle = itemView.findViewById(R.id.txt_rvStockList_title);
            txtDesc = itemView.findViewById(R.id.txt_rvStockList_desc);
            txtPrice = itemView.findViewById(R.id.txt_rvStockList_priceOff);
            txtTitlePrice = itemView.findViewById(R.id.txt_rvStockList_price);
            txtCurrency = itemView.findViewById(R.id.txt_rvStockList_currency);
            txtRemove = itemView.findViewById(R.id.txt_rvStockList_remove);
            txtRemove.setVisibility(View.VISIBLE);
            txtDesc.setVisibility(View.GONE);
            txtTitlePrice.setVisibility(View.GONE);
            txtCurrency.setVisibility(View.GONE);


        }
    }
}
