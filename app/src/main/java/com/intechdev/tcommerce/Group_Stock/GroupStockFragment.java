package com.intechdev.tcommerce.Group_Stock;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Models.ItemGroupStock;
import com.intechdev.tcommerce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupStockFragment extends BaseFragment implements GroupStockContract.View {

    private GroupStockContract.Presenter presenter;
    private TabLayout tabLayout_groupStockFragment_tab;
    private ViewPager viewPager_groupStockFragment_pager;
    private ArrayList<ItemGroupStock.Items> subList;
    private List<ItemGroupStock.Items> mainList;
    private ArrayList<ItemGroupStock.Items> allProducts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GroupStockPresenter(new TCommerceRepository());
        presenter.getGroup();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_group_stock;
    }

    @Override
    public void setupViews() {
        tabLayout_groupStockFragment_tab = rootView.findViewById(R.id.tabLayout_groupStockFragment_tab);
        viewPager_groupStockFragment_pager = rootView.findViewById(R.id.viewPager_groupStockFragment_pager);
        subList = new ArrayList<>();
        mainList = new ArrayList<>();

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void showMessage(String msg) {
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
    public void setGroup(List<ItemGroupStock.Items> items) {

        final ArrayList<ItemGroupStock.Items> data = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            data.add(items.get(i));

            if (items.get(i).getParentId() == 0){
                mainList.add(items.get(i));
            } else {
                subList.add(items.get(i));
            }
        }
        allProducts = new ArrayList<>();

        for (ItemGroupStock.Items item : items) {
            tabLayout_groupStockFragment_tab.addTab(tabLayout_groupStockFragment_tab.newTab().setText(item.getName()));
            tabLayout_groupStockFragment_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
            allProducts.add(item);
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                , mainList.size() , allProducts);
        viewPager_groupStockFragment_pager.setOffscreenPageLimit(0);

        for (int i = 0; i < mainList.size(); i++) {
            adapter.addFragment(new LoadFragment() , mainList.get(i).getName());
        }

        viewPager_groupStockFragment_pager.setAdapter(adapter);
        tabLayout_groupStockFragment_tab.setupWithViewPager(viewPager_groupStockFragment_pager);
        viewPager_groupStockFragment_pager.setRotationY(180);
        viewPager_groupStockFragment_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_groupStockFragment_tab));
        tabLayout_groupStockFragment_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                List<ItemGroupStock.Items> result = new ArrayList<>();
                for (int i = 0; i < subList.size(); i++) {
                    if (mainList.get(tab.getPosition()).getId() == subList.get(i).getParentId()){
                        result.add(subList.get(i));
                    }
                }
                LoadFragment.newInstance(data, tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        int numOfTabs;
        ArrayList<ItemGroupStock.Items> data;

        public ViewPagerAdapter(@NonNull FragmentManager fm, int numOfTabs, ArrayList<ItemGroupStock.Items> data) {
            super(fm);
            this.numOfTabs = numOfTabs;
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return LoadFragment.newInstance(data ,position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
