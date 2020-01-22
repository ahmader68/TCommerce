package com.intechdev.tcommerce.Stock_List;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.tcommerce.Adapters.HomeRecyclerAdapter;
import com.intechdev.tcommerce.Adapters.ShowMoreAdapter;
import com.intechdev.tcommerce.Advanced_Search.SearchFragment;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemListStock;
import com.intechdev.tcommerce.Models.StockListModel;
import com.intechdev.tcommerce.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StockListFragment extends BaseFragment implements StockListContract.View {

    private StockListContract.Presenter presenter;

    private TextView txt_fragmentStockList_title,
            txt_stockListFragment_sortTitle;
    private ImageView img_stockListFragment_arrange,
            img_stockListFragment_arrangeChanged;
    private RecyclerView rv_stockListFragment_product;
    private ConstraintLayout con_stockListFragment_arrangement,
            con_stockListFragment_sort,
            con_stockListFragment_filter;
    private Button btn_homeFragment_showAllSpecial;
    private ShowMoreAdapter adapter;
    private Dialog dialog;
    private Dialog progress;
    private RadioGroup rg_sortingDialog_sort;
    private Fragment fragment;
    private FragmentTransaction transaction;

    private String prodAttribs;
    private String price1;
    private String price2;
    private String isExists;
    private Map<String , String> data;

    private int flag = 0;

    private String catId = "";
    private String scatId = "";
    private String sort = "2";
    private String title = "";
    private long catIdGroupStock = 0;
    private long subCatIdGroupStock = 0;

    private Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new StockListPresenter(new TCommerceRepository());
        if(getArguments() != null) {
            Bundle bundle = getArguments();

            flag = bundle.getInt("flag");
            switch (flag) {
                case 1:
                    presenter.getNewProducts();
                    break;
                case 2:
                    presenter.getBestSellsProduct();
                    break;
                case 3:
                    presenter.getHitsProduct();
                    break;
                case 4:
                    presenter.getListStock(String.valueOf(getArguments().getLong("cat" , 0)) , String.valueOf(getArguments().getLong("subCat" , 0) ), sort);
                default:
                    //presenter.getNewProducts();
                    break;
            }
        }else {
            presenter.getNewProducts();
        }

        setPageStatrtSearchData();

        if (getArguments() != null && flag == 0 && !(getArguments().getString("prodAttributes").equals(""))){
            bundle = getArguments();
            prodAttribs = bundle.getString("prodAttributes" , "");
            price1 = bundle.getString("price1" , "");
            price2 = bundle.getString("price2" , "");
            isExists = bundle.getString("isExists" , "");

            data = new HashMap<>();

            data.put("cat", catId);
            data.put("scat", scatId);
            data.put("sort", sort);

            if (!prodAttribs.equals(""))
                data.put("prodAttribs", prodAttribs);
            if (!price1.equals(""))
                data.put("price1", price1);
            if (!price2.equals(""))
                data.put("price2", price2);
            if (!isExists.equals(""))
                data.put("isexists", isExists);

            presenter.getListStock(data);
        } else if (getArguments() != null && getArguments().getInt("flag") == 4) {
            bundle = getArguments();
            title = bundle.getString("title" , "");
            catIdGroupStock = bundle.getLong("cat" , 0);
            subCatIdGroupStock = bundle.getLong("subCat" , 0);
            flag = bundle.getInt("flag" , 0);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_stock_list;
    }

    @Override
    public void setupViews() {
        txt_fragmentStockList_title = rootView.findViewById(R.id.txt_fragmentStockList_title);
        txt_stockListFragment_sortTitle = rootView.findViewById(R.id.txt_stockListFragment_sortTitle);
        con_stockListFragment_arrangement = rootView.findViewById(R.id.con_stockListFragment_arrangement);
        con_stockListFragment_sort = rootView.findViewById(R.id.con_stockListFragment_sort);
        con_stockListFragment_filter = rootView.findViewById(R.id.con_stockListFragment_filter);
        img_stockListFragment_arrange = rootView.findViewById(R.id.img_stockListFragment_arrange);
        img_stockListFragment_arrangeChanged = rootView.findViewById(R.id.img_stockListFragment_arrangeChanged);
        rv_stockListFragment_product = rootView.findViewById(R.id.rv_stockListFragment_product);

        if (flag == 4) {
            txt_fragmentStockList_title.setText(title);
        }

        if (flag == 0 || flag == 4) {
            con_stockListFragment_filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("catId" , catIdGroupStock);
                    bundle.putLong("subCatId" , subCatIdGroupStock);
                    fragment = new SearchFragment();
                    fragment.setArguments(bundle);
                    transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                    transaction.commit();
                }
            });
        } else {
            con_stockListFragment_filter.setBackgroundColor(Color.rgb(200, 200, 200));
            con_stockListFragment_filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getViewContext(), "هیچ دست بندی انتخاب نشده است!", Toast.LENGTH_LONG).show();
                }
            });
        }

        ////////////////////////////////////////////////////////////////


        con_stockListFragment_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getViewContext());
                dialog.setContentView(R.layout.sorting_dialog);
                rg_sortingDialog_sort = dialog.findViewById(R.id.rg_sortingDialog_sort);
                dialog.show();
                selectSortType();
            }
        });
    }

    private void selectSortType() {
        rg_sortingDialog_sort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (adapter != null) {
                    if (adapter.getList() != null) {
                        adapter.getList().clear();
                        adapter.notifyDataSetChanged();
                    } else if (adapter.getItemList() != null) {
                        adapter.getItemList().clear();
                        adapter.notifyDataSetChanged();
                    }
                }
                switch (checkedId) {
                    case R.id.rb_sortingDialog_hits:
                        setPageStatrtSearchData();
                        presenter.getListStock("0", "0", "2");
                        txt_stockListFragment_sortTitle.setText("پربازدیدترین");
                        break;
                    case R.id.rb_sortingDialog_topSelling:
                        setPageStatrtSearchData();
                        presenter.getListStock("0", "0", "5");
                        txt_stockListFragment_sortTitle.setText("پرفروش ترین");
                        break;
                    case R.id.rb_sortingDialog_specialOffer:
                        setPageStatrtSearchData();
                        presenter.getListStock("0", "0", "4");
                        txt_stockListFragment_sortTitle.setText("پیشنهاد ویژه");
                        break;
                    case R.id.rb_sortingDialog_decrease:
                        setPageStatrtSearchData();
                        presenter.getListStock("0", "0", "7");
                        txt_stockListFragment_sortTitle.setText("قیمت از زیاد به کم");
                        break;
                    case R.id.rb_sortingDialog_increase:
                        setPageStatrtSearchData();
                        presenter.getListStock("0", "0", "8");
                        txt_stockListFragment_sortTitle.setText("قیمت از کم به زیاد");
                        break;
                    case R.id.rb_sortingDialog_newest:
                        setPageStatrtSearchData();
                        presenter.getListStock("0", "0", "1");
                        txt_stockListFragment_sortTitle.setText("جدیدترین");
                        break;
                }
                dialog.dismiss();
            }
        });
        setPageStatrtSearchData();
    }

    @Override
    public void setPageStatrtSearchData() {
        progress = new Dialog(getViewContext());
        progress.setTitle("در حال دریافت اطلاعات");
        progress.show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showMessage(String msg) {
        progress.dismiss();
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void showNewProduct(final StockListModel list) {
        adapter = new ShowMoreAdapter(getActivity(), list, true);
        rv_stockListFragment_product.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rv_stockListFragment_product.setAdapter(adapter);
        progress.dismiss();
        con_stockListFragment_arrangement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img_stockListFragment_arrange.getVisibility() == View.GONE) {
                    img_stockListFragment_arrange.setVisibility(View.VISIBLE);
                    img_stockListFragment_arrangeChanged.setVisibility(View.GONE);
                    adapter = new ShowMoreAdapter(getActivity(), list, false);
                    rv_stockListFragment_product.setLayoutManager(new GridLayoutManager(getViewContext(), 2, RecyclerView.VERTICAL, false));
                    rv_stockListFragment_product.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progress.dismiss();
                } else if (img_stockListFragment_arrange.getVisibility() == View.VISIBLE) {
                    img_stockListFragment_arrangeChanged.setVisibility(View.VISIBLE);
                    img_stockListFragment_arrange.setVisibility(View.GONE);
                    adapter = new ShowMoreAdapter(getActivity(), list, true);
                    rv_stockListFragment_product.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
                    rv_stockListFragment_product.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progress.dismiss();
                }
            }
        });
    }

    @Override
    public void showListStockSort(final ItemListStock list) {
        adapter = new ShowMoreAdapter(getActivity(), list, true);
        adapter.notifyDataSetChanged();
        if (img_stockListFragment_arrange.getVisibility() == View.VISIBLE) {
            img_stockListFragment_arrangeChanged.setVisibility(View.VISIBLE);
            img_stockListFragment_arrange.setVisibility(View.GONE);
        }
        rv_stockListFragment_product.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rv_stockListFragment_product.setAdapter(adapter);
        progress.dismiss();
        con_stockListFragment_arrangement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img_stockListFragment_arrange.getVisibility() == View.GONE) {
                    img_stockListFragment_arrange.setVisibility(View.VISIBLE);
                    img_stockListFragment_arrangeChanged.setVisibility(View.GONE);
                    adapter = new ShowMoreAdapter(getActivity(), list, false);
                    rv_stockListFragment_product.setLayoutManager(new GridLayoutManager(getViewContext(), 2, RecyclerView.VERTICAL, false));
                    rv_stockListFragment_product.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progress.dismiss();
                } else if (img_stockListFragment_arrange.getVisibility() == View.VISIBLE) {
                    img_stockListFragment_arrangeChanged.setVisibility(View.VISIBLE);
                    img_stockListFragment_arrange.setVisibility(View.GONE);
                    adapter = new ShowMoreAdapter(getActivity(), list, true);
                    rv_stockListFragment_product.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
                    rv_stockListFragment_product.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progress.dismiss();
                }
            }
        });
    }

    @Override
    public void showListStock(final ItemListStock list) {
        adapter = new ShowMoreAdapter(getActivity(), list, true);
        adapter.notifyDataSetChanged();
        if (img_stockListFragment_arrange.getVisibility() == View.VISIBLE) {
            img_stockListFragment_arrangeChanged.setVisibility(View.VISIBLE);
            img_stockListFragment_arrange.setVisibility(View.GONE);
        }
        rv_stockListFragment_product.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rv_stockListFragment_product.setAdapter(adapter);
        progress.dismiss();
        con_stockListFragment_arrangement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img_stockListFragment_arrange.getVisibility() == View.GONE) {
                    img_stockListFragment_arrange.setVisibility(View.VISIBLE);
                    img_stockListFragment_arrangeChanged.setVisibility(View.GONE);
                    adapter = new ShowMoreAdapter(getActivity(), list, false);
                    rv_stockListFragment_product.setLayoutManager(new GridLayoutManager(getViewContext(), 2, RecyclerView.VERTICAL, false));
                    rv_stockListFragment_product.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progress.dismiss();
                } else if (img_stockListFragment_arrange.getVisibility() == View.VISIBLE) {
                    img_stockListFragment_arrangeChanged.setVisibility(View.VISIBLE);
                    img_stockListFragment_arrange.setVisibility(View.GONE);
                    adapter = new ShowMoreAdapter(getActivity(), list, true);
                    rv_stockListFragment_product.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
                    rv_stockListFragment_product.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progress.dismiss();
                }
            }
        });
    }
}
