package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ItemTransactions;
import com.intechdev.tcommerce.R;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsHolder>{

    private Context context;
    private ItemTransactions itemTransactions;

    public TransactionsAdapter(Context context, ItemTransactions itemTransactions) {
        this.context = context;
        this.itemTransactions = itemTransactions;
    }

    @NonNull
    @Override
    public TransactionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_transactions,parent,false);
        return new TransactionsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsHolder holder, int position) {
        holder.txtCardNumber.setText(itemTransactions.getItems().get(position).getPaySerialNo());
        holder.txtPriceForPay.setText(itemTransactions.getItems().get(position).getPrice());
        holder.txtPrice.setText(itemTransactions.getItems().get(position).getPrice());
        holder.txtTracingCode.setText(itemTransactions.getItems().get(position).getTrackingCode());
        holder.txtPayDate.setText(itemTransactions.getItems().get(position).getDateSend());
    }

    @Override
    public int getItemCount() {
        return itemTransactions.getItems().size();
    }

    class TransactionsHolder extends RecyclerView.ViewHolder{

        TextView
                txtPayDate,
                txtTracingCode,
                txtPrice,
                txtPriceForPay,
                txtCardNumber;
        public TransactionsHolder(@NonNull View itemView) {
            super(itemView);
            txtPayDate = itemView.findViewById(R.id.txt_rvTransactions_payDate);
            txtTracingCode = itemView.findViewById(R.id.txt_rvTransactions_tracingCode);
            txtPrice = itemView.findViewById(R.id.txt_rvTransactions_price);
            txtPriceForPay = itemView.findViewById(R.id.txt_rvTransactions_priceForPay);
            txtCardNumber = itemView.findViewById(R.id.txt_rvTransactions_cardNumber);
        }
    }
}
