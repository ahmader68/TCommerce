package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Basket.BasketContract;
import com.intechdev.tcommerce.Models.ItemOrdersBasket;
import com.intechdev.tcommerce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductInBasketAdapter extends RecyclerView.Adapter<ProductInBasketAdapter.ProductBasketHolder> {

    private int count;
    private Context context;
    private BasketContract.View view;

    private ItemOrdersBasket itemOrdersBasket;
    List<String> counts;
    ArrayAdapter<String> countAdapter;

    public ProductInBasketAdapter(Context context,BasketContract.View view,ItemOrdersBasket itemOrdersBasket) {
        this.context = context;
        this.itemOrdersBasket = itemOrdersBasket;
        this.view = view;
        count = 0;
        counts = new ArrayList<>();
        for(int i = 1; i <= 10;i++){
            counts.add(i+"");
        }
    }

    @NonNull
    @Override
    public ProductBasketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_product_basket,parent,false);
        return new ProductBasketHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductBasketHolder holder, final int position) {

        countAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,counts);
        countAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.txtFinalPrice.setText(String.valueOf(itemOrdersBasket.getItems().get(position).getTotalPrice()));
        holder.txtDiscount.setText(String.valueOf(itemOrdersBasket.getItems().get(position).getDiscount()));
        holder.txtTotalPrice.setText(String.valueOf(itemOrdersBasket.getItems().get(position).getTotalPrice()));
        holder.txtGuarantee.setText("گارانتی اصالت و سلامت فیزیکی کالا");
        holder.txtColor.setText("بدون رنگ");
        holder.txtProductDetails.setText(itemOrdersBasket.getItems().get(position).getProductAttributes());
        holder.txtProductTitle.setText(itemOrdersBasket.getItems().get(position).getProductTitle());
        Picasso.get().load(itemOrdersBasket.getItems().get(position).getProductimage()).into(holder.imgProductImage);

        holder.spCount.setAdapter(countAdapter);
        holder.spCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = Integer.parseInt(holder.spCount.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int usrid = itemOrdersBasket.getItems().get(position).getUsrid();
                int OrderId = itemOrdersBasket.getItems().get(position).getID();

                view.deleteFromBasket(usrid,OrderId,position,itemOrdersBasket);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemOrdersBasket.getItems().size();
    }

    class ProductBasketHolder extends RecyclerView.ViewHolder{

        TextView
                txtProductTitle,
                txtProductDetails,
                txtColor,
                txtGuarantee,
                txtTotalPrice,
                txtDiscount,
                txtFinalPrice;

        Spinner spCount;

        ImageView imgProductImage;

        Button btnRemove;

        public ProductBasketHolder(@NonNull View itemView) {
            super(itemView);
            txtProductTitle = itemView.findViewById(R.id.txt_rvProductBasket_productName);
            txtProductDetails = itemView.findViewById(R.id.txt_rvProductBasket_productDetails);
            txtColor = itemView.findViewById(R.id.txt_rvProductBasket_color);
            txtGuarantee = itemView.findViewById(R.id.txt_rvProductBasket_guarantee);
            txtTotalPrice = itemView.findViewById(R.id.txt_rvProductBasket_totalPrice);
            txtDiscount = itemView.findViewById(R.id.txt_rvProductBasket_discount);
            txtFinalPrice = itemView.findViewById(R.id.txt_rvProductBasket_finalPrice);

            spCount = itemView.findViewById(R.id.sp_rvProductBasket_count);

            imgProductImage = itemView.findViewById(R.id.img_rvProductBasket_product);

            btnRemove = itemView.findViewById(R.id.btn_rvProductBasket_removeProduct);
        }
    }
}
