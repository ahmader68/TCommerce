package com.intechdev.tcommerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Advanced_Search.SearchContract;
import com.intechdev.tcommerce.Models.AddressModel;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;

import static com.intechdev.tcommerce.Adapters.AdvanceSearchMainAdapter.entireSubAtter;

public class AdvanceSearchSubMainAdapter extends RecyclerView.Adapter<AdvanceSearchSubMainAdapter.SubMainHolder> {

    private Context context;
    private List<ItemAdvanceSearch.ItemMain> list;
    private List<ItemAdvanceSearch.ItemMain> mListMasterData;
    private List<ItemAdvanceSearch.ItemMain> mainList;
    private String type;
    public SearchContract.View view;
    private int pos;
    private int oldPos = -1;

    private SubMainHolder oldHolder;

    public AdvanceSearchSubMainAdapter(Context context, List<ItemAdvanceSearch.ItemMain> mainItem,
                                       List<ItemAdvanceSearch.ItemMain> list, String type,
                                       SearchContract.View view , int position) {
        this.context = context;
        this.list = list;
        this.mListMasterData = list;
        this.mainList = mainItem;
        this.type = type;
        this.view = view;
        this.pos = position;

        for (int i = 0; i < entireSubAtter.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (entireSubAtter.get(i) == this.list.get(j)) {
                    this.list.get(j).setIsSelected(entireSubAtter.get(i).getIsSelected());
                }
            }
        }
    }

    @NonNull
    @Override
    public SubMainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (type != null) {
            if (type.equals("Select")) {
                view = LayoutInflater.from(context).inflate(R.layout.list_checkbox_submain_search, parent, false);
            } else if (type.equals("Radio")) {
                view = LayoutInflater.from(context).inflate(R.layout.list_radio_submain_search, parent, false);
            }
        }
        return new SubMainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubMainHolder holder, final int position) {
        final ItemAdvanceSearch.ItemMain item = list.get(position);

//        if (oldPos != pos){
//            oldPos = this.pos;
//            oldHolder = null;
//        }

        if (type.equals("Select")) {
            holder.chb_subMain_search.setText(item.getName());
            if (item.getIsSelected() != null && item.getIsSelected().equals("1")) {
                holder.chb_subMain_search.setChecked(true);
            } else {
                holder.chb_subMain_search.setChecked(false);
            }
            holder.chb_subMain_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getIsSelected() != null && item.getIsSelected().equals("1")) {
                        item.setIsSelected("0");
                        for (int i = 0; i < mainList.size(); i++) {
                            if (item.getId() == mainList.get(i).getId()) {
                                mainList.get(i).setIsSelected("0");
                            }
                        }
                    } else {
                        item.setIsSelected("1");
                        for (int i = 0; i < mainList.size(); i++) {
                            if (item.getId() == mainList.get(i).getId()) {
                                mainList.get(i).setIsSelected("1");
                            }
                        }
                    }
                }
            });
        } else if (type.equals("Radio")) {
            holder.rb_subMain_search.setText(item.getName());

            if (item.getIsSelected() != null && item.getIsSelected().equals("1")){
                holder.rb_subMain_search.setChecked(true);
            } else if (item.getIsSelected() != null && item.getIsSelected().equals("0")){
                holder.rb_subMain_search.setChecked(false);
            }

            holder.rb_subMain_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if (oldHolder == null) {
//                        oldHolder.rb_subMain_search.setChecked(false);
//                        for (int i = 0; i < entireSubAtter.size(); i++) {
//                            if (item.getId() != entireSubAtter.get(i).getId()){
//                                entireSubAtter.get(i).setIsSelected("0");
//                            }
//                        }
//                        oldHolder = holder;
//                    }


                    item.setIsSelected("1");
                    for (int i = 0; i < entireSubAtter.size(); i++) {
                        if (entireSubAtter.get(i).getId() == item.getId()){
                            entireSubAtter.get(i).setIsSelected("1");
                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        if (item.getId() != list.get(i).getId()){
                            list.get(i).setIsSelected("0");
                        }
                    }
                    for (int i = 0; i < entireSubAtter.size(); i++) {
                        if (item.getId() == entireSubAtter.get(i).getId()){
                            entireSubAtter.get(i).setIsSelected("1");
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (type != null) {
            switch (type) {
                case "Select":
                    return 0;
                case "Radio":
                    return 1;
                case "FromTo":
                    return 2;
            }
        }
        return 0;
    }

    public List<ItemAdvanceSearch.ItemMain> getList() {
        //view.showList(list);
        return mainList;
    }

    public void remove(AddressModel city) {
        int position = list.indexOf(city);
        if (position > -1) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clearChecked() {
        for (ItemAdvanceSearch.ItemMain item : list) {
            item.setIsSelected("0");
            item.setPrice1("");
            item.setPrice2("");
        }

        for (ItemAdvanceSearch.ItemMain item : mListMasterData) {
            item.setIsSelected("0");
            item.setPrice1("");
            item.setPrice2("");
        }
        notifyDataSetChanged();
    }

    class SubMainHolder extends RecyclerView.ViewHolder {

        private CheckBox chb_subMain_search;
        private RadioButton rb_subMain_search;
        private ConstraintLayout con_ch_subMain_search, con_rb_subMain_search;

        SubMainHolder(@NonNull View itemView) {
            super(itemView);
            if (type.equals("Select")) {
                con_ch_subMain_search = itemView.findViewById(R.id.con_ch_subMain_search);
                chb_subMain_search = itemView.findViewById(R.id.chb_subMain_search);
            } else if (type.equals("Radio")) {
                con_rb_subMain_search = itemView.findViewById(R.id.con_rb_subMain_search);
                rb_subMain_search = itemView.findViewById(R.id.rb_subMain_search);
            }
        }
    }
}
