package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ItemDiscounts;
import com.intechdev.tcommerce.R;

public class DiscountsAdapter extends RecyclerView.Adapter<DiscountsAdapter.DiscountsHolder> {
    private ItemDiscounts itemDiscounts;
    private Context context;

    public DiscountsAdapter(ItemDiscounts itemDiscounts, Context context) {
        this.itemDiscounts = itemDiscounts;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscountsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_discounts,parent,false);
        return new DiscountsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountsHolder holder, int position) {
        holder.txtDiscountTitle.setText(itemDiscounts.getItems().get(position).getTitle());
        holder.txtStartDate.setText(itemDiscounts.getItems().get(position).getDiscountCreateDate());
        holder.txtEndDate.setText("ندارد");
        holder.txtDiscountValue.setText(itemDiscounts.getItems().get(position).getMizanTakhfif());
        holder.txtMinimumBuy.setText("فعلا مقدار ندارد");
        holder.txtDiscountCode.setText(itemDiscounts.getItems().get(position).getDiscountCode());
    }

    @Override
    public int getItemCount() {
        return itemDiscounts.getItems().size();
    }

    class DiscountsHolder extends RecyclerView.ViewHolder{

        TextView
                txtDiscountTitle,
                txtStartDate,
                txtEndDate,
                txtDiscountValue,
                txtMinimumBuy,
                txtDiscountCode;
        public DiscountsHolder(@NonNull View itemView) {
            super(itemView);
            txtDiscountTitle = itemView.findViewById(R.id.txt_rvDiscounts_discountTitle);
            txtStartDate = itemView.findViewById(R.id.txt_rvDiscounts_startDate);
            txtEndDate = itemView.findViewById(R.id.txt_rvDiscounts_endDate);
            txtDiscountValue = itemView.findViewById(R.id.txt_rvDiscounts_discountValue);
            txtMinimumBuy = itemView.findViewById(R.id.txt_rvDiscounts_minimumBuy);
            txtDiscountCode = itemView.findViewById(R.id.txt_rvDiscounts_discountCode);
        }
    }
}
