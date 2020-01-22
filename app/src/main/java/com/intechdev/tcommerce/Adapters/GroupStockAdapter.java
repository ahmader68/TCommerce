package com.intechdev.tcommerce.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Models.ItemGroupStock;
import com.intechdev.tcommerce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GroupStockAdapter extends RecyclerView.Adapter<GroupStockAdapter.GroupViewHolder> {

    private Activity context;
    private List<ItemGroupStock.Items> subLists;
    private List<ItemGroupStock.Items> mainList;

    public GroupStockAdapter(Activity context, List<ItemGroupStock.Items> subList, List<ItemGroupStock.Items> mainList) {
        this.context = context;
        this.subLists = subList;
        this.mainList = mainList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_stock_load_fragment, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupViewHolder holder, int position) {
        List<ItemGroupStock.Items> subItemsList = new ArrayList<>();
        ItemGroupStock.Items items = subLists.get(position);
        holder.txt_rvStockGroup_subject.setText(items.getName());
        Picasso.get().load(items.getIcon()).into(holder.img_rvStockGroup_catImage);

        for (int i = 0; i < subLists.size(); i++) {
            if (items.getId() == subLists.get(i).getId()) {
                subItemsList.add(subLists.get(i));
            }
        }


        GroupStockSubAdapter adapter = new GroupStockSubAdapter(context, subItemsList);
        holder.rv_rv_group_stock_load_subTitle.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.rv_rv_group_stock_load_subTitle.setAdapter(adapter);

        holder.con_rv_group_stock_load_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.con_rv_group_stock_load_subTitle.getVisibility() == View.GONE) {
                    holder.con_rv_group_stock_load_subTitle.setVisibility(View.VISIBLE);
                    holder.img_rvStockGroup_arrow.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_drop_up, context.getTheme()));
                } else if (holder.con_rv_group_stock_load_subTitle.getVisibility() == View.VISIBLE) {
                    holder.con_rv_group_stock_load_subTitle.setVisibility(View.GONE);
                    holder.img_rvStockGroup_arrow.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_drop_down, context.getTheme()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subLists.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_rvStockGroup_subject;
        private RecyclerView rv_rv_group_stock_load_subTitle;
        private ImageView img_rvStockGroup_arrow, img_rvStockGroup_catImage;
        private ConstraintLayout con_rv_group_stock_load_subject, con_rv_group_stock_load_subTitle;

        GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_rvStockGroup_subject = itemView.findViewById(R.id.txt_rvStockGroup_subject);
            rv_rv_group_stock_load_subTitle = itemView.findViewById(R.id.rv_rv_group_stock_load_subTitle);
            img_rvStockGroup_arrow = itemView.findViewById(R.id.img_rvStockGroup_arrow);
            img_rvStockGroup_catImage = itemView.findViewById(R.id.img_rvStockGroup_catImage);
            con_rv_group_stock_load_subject = itemView.findViewById(R.id.con_rv_group_stock_load_subject);
            con_rv_group_stock_load_subTitle = itemView.findViewById(R.id.con_rv_group_stock_load_subTitle);
        }
    }
}
