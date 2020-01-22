package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ItemOrders;
import com.intechdev.tcommerce.R;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryHolder> {

    private Context context;

    private ItemOrders itemOrders;

    public OrderHistoryAdapter(Context context,ItemOrders itemOrders) {
        this.context = context;
        this.itemOrders = itemOrders;
    }

    @NonNull
    @Override
    public OrderHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_order_history,parent,false);
        return new OrderHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryHolder holder, int position) {
        holder.txtOrderMan.setText(itemOrders.getItems().get(position).getBuyerFullName());
        holder.txtFactorPrice.setText(itemOrders.getItems().get(position).getTotalToPay());
        holder.txtPostPrice.setText(itemOrders.getItems().get(position).getPostAmount());
        holder.txtTracingCode.setText(itemOrders.getItems().get(position).getTrackingCode());
        holder.txtCreateDate.setText(itemOrders.getItems().get(position).getCreateDate());
        holder.txtOrdDate.setText(itemOrders.getItems().get(position).getOrderDate());
        holder.txtProductCount.setText(itemOrders.getItems().get(position).getTotalItemCount());
    }

    @Override
    public int getItemCount() {
        return itemOrders.getItems().size();
    }

    class OrderHistoryHolder extends RecyclerView.ViewHolder{

        TextView
                txtOrderMan,
                txtFactorPrice,
                txtPostPrice,
                txtTracingCode,
                txtCreateDate,
                txtOrdDate,
                txtProductCount;
        public OrderHistoryHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderMan = itemView.findViewById(R.id.txt_rvOrderHistory_orderMan);
            txtFactorPrice = itemView.findViewById(R.id.txt_rvOrderHistory_factorPrice);
            txtPostPrice = itemView.findViewById(R.id.txt_rvOrderHistory_postPrice);
            txtTracingCode = itemView.findViewById(R.id.txt_rvOrderHistory_tracingCode);
            txtCreateDate = itemView.findViewById(R.id.txt_rvOrderHistory_createDate);
            txtOrdDate = itemView.findViewById(R.id.txt_rvOrderHistory_ordDate);
            txtProductCount = itemView.findViewById(R.id.txt_rvOrderHistory_productCount);
        }
    }
}
