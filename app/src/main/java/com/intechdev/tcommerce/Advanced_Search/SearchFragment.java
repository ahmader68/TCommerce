package com.intechdev.tcommerce.Advanced_Search;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.intechdev.tcommerce.Adapters.AdvanceSearchMainAdapter;
import com.intechdev.tcommerce.Adapters.AdvanceSearchSubMainAdapter;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemAdvanceSearch;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Stock_List.StockListFragment;

import java.util.List;
import java.util.Objects;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private SearchContract.Presenter presenter;
    private ImageView imgToolbarSearch;
    private TextView txtToolbarMenu;
    private RecyclerView rv_searchFragment_filters , rv_searchFragment_Options;
    private EditText edt_searchFragment_search;
    private ConstraintLayout con_searchFragment_remove;
    private Switch switch_fragmentSearch_exist;
    private AdvanceSearchMainAdapter adapter;
    private AdvanceSearchSubMainAdapter adapterSub;
    private Button btn_searchFragment_filter;
    private static List<ItemAdvanceSearch.ItemMain> lstItemAdvSearch;
    private static String isExists = "";
    private Fragment fragment;
    private FragmentTransaction transaction;

    private Long catId , subCatId;
    private String searchText;
    private Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(new TCommerceRepository());

        if (getArguments() != null && getArguments().getLong("catId") != 0) {

            bundle = getArguments();
            catId = bundle.getLong("catId", 0);
            subCatId = bundle.getLong("subCatId", 0);

            presenter.getAdvanceSearchItem(catId.intValue());
        } else if (getArguments() != null && !(Objects.equals(getArguments().getString("search text"), ""))){
            searchText = bundle.getString("searchText", "");
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void setupViews() {
        rv_searchFragment_filters = rootView.findViewById(R.id.rv_searchFragment_filters);
        rv_searchFragment_Options = rootView.findViewById(R.id.rv_searchFragment_Options);
        edt_searchFragment_search = rootView.findViewById(R.id.edt_searchFragment_search);
        con_searchFragment_remove = rootView.findViewById(R.id.con_searchFragment_remove);
        switch_fragmentSearch_exist = rootView.findViewById(R.id.switch_fragmentSearch_exist);
        btn_searchFragment_filter = rootView.findViewById(R.id.btn_searchFragment_filter);

        imgToolbarSearch = Objects.requireNonNull(getActivity()).findViewById(R.id.imgToolbarMenu);
        txtToolbarMenu = getActivity().findViewById(R.id.txtToolbarMenu);
        imgToolbarSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_close , getActivity().getTheme()));
        txtToolbarMenu.setText("جستجو");

        edt_searchFragment_search.setText(searchText);


        imgToolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new StockListFragment();
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        btn_searchFragment_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                StringBuilder prodAttributes = new StringBuilder();
                String price1 = "";
                String price2 = "";
                lstItemAdvSearch = adapterSub.getList();
                for (ItemAdvanceSearch.ItemMain item : lstItemAdvSearch){
                    if(item.getParentId().length() > 0){

                        if(item.getParentId().equals("-1")) {
                            price1 = item.getPrice1();
                            price2 = item.getPrice2();
                        } else if(item.getIsSelected() != null && item.getIsSelected().equals("1")){
                            prodAttributes.append(item.getId()).append(",");
                        }
                    }
                }

                if(switch_fragmentSearch_exist.isChecked()){
                    isExists = "1";
                } else {
                    isExists = "";
                }

                /*
                String[] separated = prodAttributes.split(",");
                separated[0]; // this will contain "Fruit: they taste good"
                separated[1]; // this will contain "very nice actually"

                 */

                bundle.putString("prodAttributes", prodAttributes.toString());
                bundle.putString("price1",price1);
                bundle.putString("price2",price2);
                bundle.putString("isExists",isExists);
                fragment = new StockListFragment();
                fragment.setArguments(bundle);
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
        imgToolbarSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu , getActivity().getTheme()));
        txtToolbarMenu.setText("تیکامرس");
    }

    @Override
    public void showMainSearch(List<ItemAdvanceSearch.ItemMain> itemAdvanceSearch) {
        adapter = new AdvanceSearchMainAdapter(this,getViewContext() , itemAdvanceSearch);
        rv_searchFragment_filters.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.VERTICAL , false));
        rv_searchFragment_filters.setAdapter(adapter);
    }

    @Override
    public void showSubMainSearch(List<ItemAdvanceSearch.ItemMain> mainItem , List<ItemAdvanceSearch.ItemMain> item , String type , int position) {
        adapterSub = new AdvanceSearchSubMainAdapter(getViewContext() , mainItem , item , type , this , position);
        adapterSub.notifyDataSetChanged();
        rv_searchFragment_Options.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.VERTICAL , false));
        rv_searchFragment_Options.setAdapter(adapterSub);
    }

    @Override
    public void showList(List<ItemAdvanceSearch.ItemMain> items) {
        lstItemAdvSearch = items;
    }
}
