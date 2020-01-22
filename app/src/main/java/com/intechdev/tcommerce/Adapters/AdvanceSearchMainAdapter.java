package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Advanced_Search.SearchContract;
import com.intechdev.tcommerce.Advanced_Search.SearchFragment;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvanceSearchMainAdapter extends RecyclerView.Adapter<AdvanceSearchMainAdapter.SearchHolder> {

    private Context context;
    private List<ItemAdvanceSearch.ItemMain> itemAdvanceSearch;
    private SearchContract.View view;

    private List<ItemAdvanceSearch.ItemMain> items;
    private List<ItemAdvanceSearch.ItemMain> itemMain;

    //////////////////////////////////////////////////////
    public static List<ItemAdvanceSearch.ItemMain> entireSubAtter;
    //////////////////////////////////////////////////////

    public AdvanceSearchMainAdapter(SearchContract.View view,Context context , List<ItemAdvanceSearch.ItemMain> itemAdvanceSearch){
        this.context = context;
        this.itemAdvanceSearch = itemAdvanceSearch;
        this.view = view;

        items = itemAdvanceSearch;
        itemMain = new ArrayList<>();
        entireSubAtter = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getParentId().equals("0")) {
                itemMain.add(items.get(i));
            } else {
                entireSubAtter.add(items.get(i));
            }
        }
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_main_search , parent , false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, final int position) {
        holder.txt_itemMain_item.setText(itemMain.get(position).getName());

        holder.con_itemMain_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ItemAdvanceSearch.ItemMain> subMain = new ArrayList<>();
                for (int i = 0; i < itemAdvanceSearch.size(); i++) {
                    if (itemAdvanceSearch.get(i).getParentId().equals(String.valueOf(itemMain.get(position).getId()))) {
                        subMain.add(itemAdvanceSearch.get(i));
                    }
                }
                String type = itemMain.get(position).getType();
                view.showSubMainSearch(entireSubAtter , subMain , type , position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemMain.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder {

        private TextView txt_itemMain_item;
        private ConstraintLayout con_itemMain_item;

        SearchHolder(@NonNull View itemView) {
            super(itemView);

            txt_itemMain_item = itemView.findViewById(R.id.txt_itemMain_item);
            con_itemMain_item = itemView.findViewById(R.id.con_itemMain_item);
        }
    }
}
