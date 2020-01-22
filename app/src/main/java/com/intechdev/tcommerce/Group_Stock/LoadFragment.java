package com.intechdev.tcommerce.Group_Stock;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.GroupStockAdapter;
import com.intechdev.tcommerce.Models.ItemGroupStock;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;

public class LoadFragment extends Fragment {
    private List<ItemGroupStock.Items> mainList = new ArrayList<>();
    private List<ItemGroupStock.Items> subList = new ArrayList<>();
    public static final String ARG_PRODUCTS = "PRODS";
    private static List<ItemGroupStock.Items> allProducts;
    int position = 0;
    private RecyclerView rv_groupStockFragment_pager;
    private GroupStockAdapter groupStockAdapter = null;
    private List<ItemGroupStock.Items> result;
    private View view;

    public static LoadFragment newInstance(List<ItemGroupStock.Items> data, int position) {
        LoadFragment fragment = new LoadFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PRODUCTS, (ArrayList<? extends Parcelable>) data);
        args.putInt("KEY_POSITION",position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            allProducts = getArguments().getParcelableArrayList(ARG_PRODUCTS);
            this.position = getArguments().getInt("KEY_POSITION");
        }

        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getParentId() == 0){
                mainList.add(allProducts.get(i));
            } else {
                subList.add(allProducts.get(i));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        result = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++) {
            if (mainList.get(position).getId() == subList.get(i).getParentId()) {
                result.add(subList.get(i));
            }
        }
        view = inflater.inflate(R.layout.fragment_load_group_stock, container, false);
        rv_groupStockFragment_pager = view.findViewById(R.id.rv_groupStockFragment_pager);
        rv_groupStockFragment_pager.setLayoutManager(new LinearLayoutManager(getActivity()));
        groupStockAdapter = new GroupStockAdapter(getActivity(), result , mainList);
        rv_groupStockFragment_pager.setAdapter(groupStockAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rv_groupStockFragment_pager.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_groupStockFragment_pager.setAdapter(new GroupStockAdapter(getActivity() , result , allProducts));
    }
}
