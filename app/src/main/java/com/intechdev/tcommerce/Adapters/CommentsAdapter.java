package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ItemComments;
import com.intechdev.tcommerce.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder>{
    private ItemComments itemComments;
    private Context context;

    public CommentsAdapter(ItemComments itemComments, Context context) {
        this.itemComments = itemComments;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_comments,parent,false);
        return new CommentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        holder.txtTitle.setText(itemComments.getItems().get(position).getSubject());
        holder.txtName.setText(itemComments.getItems().get(position).getSenderName());
        holder.txtDate.setText(itemComments.getItems().get(position).getSendDate());
        holder.txtContent.setText(itemComments.getItems().get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return itemComments.getItems().size();
    }

    class CommentsHolder extends RecyclerView.ViewHolder{
        TextView
                txtTitle,
                txtName,
                txtDate,
                txtContent;
        CommentsHolder(@NonNull View itemView) {
            super(itemView);
            txtContent = itemView.findViewById(R.id.txt_rvComments_content);
            txtDate = itemView.findViewById(R.id.txt_rvComments_date);
            txtName = itemView.findViewById(R.id.txt_rvComments_name);
            txtTitle = itemView.findViewById(R.id.txt_rvComments_title);

        }
    }
}
