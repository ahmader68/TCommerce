package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ProductList;
import com.intechdev.tcommerce.Models.StockListModel;
import com.intechdev.tcommerce.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder> {

    private Context context;
    private StockListModel stockList;

    public HomeRecyclerAdapter(Context context , StockListModel stockList) {
        this.context = context;
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_home_product , parent , false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        StockListModel.Product stockModel = stockList.getItems().get(position);
//"https://sahba-co.com/images/package/dahua/08.jpg"
        Picasso.get().load(stockModel.getIcon() == null ? "https://sahba-co.com/images/package/dahua/08.jpg" : stockModel.getIcon()).into(holder.img_rvHome_product);
        holder.txt_rvHome_productName.setText(stockModel.getName());
        holder.txt_rvHome_price.setText(stockModel.getPrice());

        if (stockModel.getPriceOffer() != null) {
            holder.con_rvHome_holderOne.setVisibility(View.VISIBLE);
            holder.txt_rvHome_priceOff.setText(stockModel.getPriceOffer());
        }

    }

    @Override
    public int getItemCount() {
        return stockList.getItems().size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView img_rvHome_product;
        TextView txt_rvHome_productName,
                txt_rvHome_currencyOff,
                txt_rvHome_priceOff,
                txt_rvHome_currency,
                txt_rvHome_price;
        ConstraintLayout con_rvHome_holderOne;

        HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            img_rvHome_product = itemView.findViewById(R.id.img_rvHome_product);
            txt_rvHome_productName = itemView.findViewById(R.id.txt_rvHome_productName);
            txt_rvHome_currencyOff = itemView.findViewById(R.id.txt_rvHome_currencyOff);
            txt_rvHome_priceOff = itemView.findViewById(R.id.txt_rvHome_priceOff);
            txt_rvHome_currency = itemView.findViewById(R.id.txt_rvHome_currency);
            txt_rvHome_price = itemView.findViewById(R.id.txt_rvHome_price);
            con_rvHome_holderOne = itemView.findViewById(R.id.con_rvHome_holderOne);
        }
    }
}
