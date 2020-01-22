package com.intechdev.tcommerce.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Advanced_Search.SearchFragment;
import com.intechdev.tcommerce.Models.ItemGroupStock;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Stock_List.StockListFragment;

import java.util.List;
import java.util.Objects;

public class GroupStockSubAdapter extends RecyclerView.Adapter<GroupStockSubAdapter.SubHolder> {

    private Activity context;
    private List<ItemGroupStock.Items> list;
    private FragmentTransaction transAction;

    public GroupStockSubAdapter(Activity context , List<ItemGroupStock.Items> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_group_stock , parent , false);
        return new SubHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubHolder holder, int position) {
        final ItemGroupStock.Items items = list.get(position);
        holder.txt_rvGroupStock_title.setText(items.getName());

        holder.con_rvGroupStock_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("title" , items.getName());
                bundle.putLong("cat" , items.getParentId());
                bundle.putLong("subCat" , items.getId());
                bundle.putInt("flag" , 4);

                StockListFragment stockListFragment = new StockListFragment();
                stockListFragment.setArguments(bundle);
                transAction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                transAction.replace(R.id.frm_mainFragment_mainLayout, stockListFragment);
                transAction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SubHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout con_rvGroupStock_container;
        private TextView txt_rvGroupStock_title;

        SubHolder(@NonNull View itemView) {
            super(itemView);
            con_rvGroupStock_container = itemView.findViewById(R.id.con_rvGroupStock_container);
            txt_rvGroupStock_title = itemView.findViewById(R.id.txt_rvGroupStock_title);
        }
    }
}
