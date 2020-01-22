package com.intechdev.tcommerce.Main_Page;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.intechdev.tcommerce.Activities.MainActivity;
import com.intechdev.tcommerce.Advanced_Search.SearchFragment;
import com.intechdev.tcommerce.Base.BaseFragment;
import com.intechdev.tcommerce.Basket.BasketFragment;
import com.intechdev.tcommerce.Blog_List.BlogListFragment;
import com.intechdev.tcommerce.Change_Pass.ChangePassFragment;
import com.intechdev.tcommerce.Data.TCommerceRepository;
import com.intechdev.tcommerce.Discounts.DiscountsFragment;
import com.intechdev.tcommerce.Downloads.DownloadsFragment;
import com.intechdev.tcommerce.Favourites.FavouritesFragment;
import com.intechdev.tcommerce.Fragments.WebViewFragment;
import com.intechdev.tcommerce.Group_Stock.GroupStockFragment;
import com.intechdev.tcommerce.History_Orders.HistoryFragment;
import com.intechdev.tcommerce.Home.HomeFragment;
import com.intechdev.tcommerce.Fragments.MainRegisterLoginFragment;
import com.intechdev.tcommerce.Message.MessageFragment;
import com.intechdev.tcommerce.Models.InitProg;
import com.intechdev.tcommerce.My_Addresses.MyAddressFragment;
import com.intechdev.tcommerce.My_Profile.MyProfileFragment;
import com.intechdev.tcommerce.Profile.ProfileFragment;
import com.intechdev.tcommerce.R;
import com.intechdev.tcommerce.Stock_Details.StockDetailsFragment;
import com.intechdev.tcommerce.Stock_List.StockListFragment;
import com.intechdev.tcommerce.Support.SupportFragment;
import com.intechdev.tcommerce.TransActions.TransActionsFragment;

import java.util.Objects;

public class MainFragment extends BaseFragment implements MainContract.View {
    private Fragment fragment, fragmentMenu;
    private FragmentTransaction transaction;
    private ImageView imgToolbarMenu;
    public static TextView txtBasket;
    public static int fragmentCount;
    private NavigationView navigation;
    private DrawerLayout drawer;

    private final int PERMISSIONDOWNLOAD = 222;
    private String url;
    private MainContract.Presenter presenter;
    private Dialog noInternetDialog;
    private SharedPreferences sharedPreferences;

    private Bundle bundle;

    FragmentManager fragmentManager;

    private int basketCount;

    private boolean checkExit = false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(new TCommerceRepository());
        boolean checkInternet = isInternetAvailable();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        basketCount = sharedPreferences.getInt("basket", 0);
        bundle = new Bundle();
//        noInternetDialog = new Dialog(getViewContext());
//        noInternetDialog.setCancelable(false);
//        noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        noInternetDialog.setContentView(R.layout.dialog_no_internet);
//        noInternetDialog.getWindow().setLayout(1000, 750);
//        if (checkInternet) {
//            noInternetDialog.dismiss();
////            setupViews();
//        } else {


//            TextView txtWifi = noInternetDialog.findViewById(R.id.txt_dialogNoInternet_Wifi);
//            TextView txtGrps = noInternetDialog.findViewById(R.id.txt_dialogNoInternet_gprs);
//            txtWifi.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
//                    startActivity(intent);
//
//                }
//            });
//            txtGrps.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent;
//                    intent = new Intent();
//                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
//                    startActivity(intent);
//
//                }
//            });
//            noInternetDialog.show();
        // }
        presenter.getInit();
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void setupViews() {

        ConstraintLayout con_bottomNav_profile = rootView.findViewById(R.id.con_bottomNav_profile);
        ConstraintLayout con_bottomNav_search = rootView.findViewById(R.id.con_bottomNav_search);
        ConstraintLayout con_bottomNav_home = rootView.findViewById(R.id.con_bottomNav_home);
        ConstraintLayout con_bottomNav_cat = rootView.findViewById(R.id.con_bottomNav_cat);
        ConstraintLayout con_bottomNav_back = rootView.findViewById(R.id.con_bottomNav_back);
        ConstraintLayout conBasket = rootView.findViewById(R.id.rlToolbarShopping);
        final ConstraintLayout con_bottomNav_searchOpen = rootView.findViewById(R.id.con_bottomNav_searchOpen);
        final ConstraintLayout con_bottomNav_container = rootView.findViewById(R.id.con_bottomNav_container);

        conBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new BasketFragment();

                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        imgToolbarMenu = rootView.findViewById(R.id.imgToolbarMenu);
        txtBasket = rootView.findViewById(R.id.txtToolbarCountBasket);
        navigation = rootView.findViewById(R.id.navigation);
        drawer = rootView.findViewById(R.id.drawer);

        txtBasket.setText(String.valueOf(basketCount));

        imgToolbarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_drawer_blog:
                        fragmentMenu = new BlogListFragment();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_drawer_register:
                        bundle.putInt("flag", 0);
                        fragmentMenu = new MainRegisterLoginFragment();
                        fragmentMenu.setArguments(bundle);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_drawer_enter:
                        bundle.putInt("flag", 1);
                        fragmentMenu = new MainRegisterLoginFragment();
                        fragmentMenu.setArguments(bundle);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_drawer_exit:
                        sharedPreferences.edit().clear().apply();
                        showMessage("با موفقیت از حساب کاربری خود خارج شدید");
                        fragmentMenu = new MainFragment();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragmentMenu);
                transaction.commit();
                return true;
            }
        });

        fragment = new HomeFragment();
        transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
        transaction.commitAllowingStateLoss();

        con_bottomNav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new ProfileFragment();
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        con_bottomNav_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con_bottomNav_searchOpen.setVisibility(View.VISIBLE);
                con_bottomNav_container.setVisibility(View.GONE);
                ImageView img_bottomNav_closeSearch = rootView.findViewById(R.id.img_bottomNav_closeSearch);
                final TextInputEditText edt_bottomNav_searchOpen = rootView.findViewById(R.id.edt_bottomNav_searchOpen);
                ImageView img_bottomNav_doSearch = rootView.findViewById(R.id.img_bottomNav_doSearch);

                img_bottomNav_closeSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        con_bottomNav_searchOpen.setVisibility(View.GONE);
                        con_bottomNav_container.setVisibility(View.VISIBLE);
                    }
                });

                img_bottomNav_doSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Objects.requireNonNull(edt_bottomNav_searchOpen.getText()).toString().equals("") ||
                                Objects.requireNonNull(edt_bottomNav_searchOpen.getText()).toString().equals(" ") ||
                                Objects.requireNonNull(edt_bottomNav_searchOpen.getText()).toString().equals("  ") ||
                                Objects.requireNonNull(edt_bottomNav_searchOpen.getText()).toString().equals("   ")){
                            Toast.makeText(getViewContext(), "هیچ کلمه ای درج نشده است.", Toast.LENGTH_SHORT).show();
                        } else {
                            String searchText = edt_bottomNav_searchOpen.getText().toString();

                            fragment = new SearchFragment();

                            Bundle bundleN =  new Bundle();

                            bundleN.putString("searchText" , searchText);

                            fragment.setArguments(bundleN);

                            transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                            transaction.commit();
                            hideKeyboardFrom(getViewContext() , Objects.requireNonNull(getView()));
                            con_bottomNav_searchOpen.setVisibility(View.GONE);
                            con_bottomNav_container.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

        con_bottomNav_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new HomeFragment();
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        con_bottomNav_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new GroupStockFragment();
                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });

        con_bottomNav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragment = new StockListFragment();
//                transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
//                transaction.commit();
                Fragment backFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frm_mainFragment_mainLayout);
                if ((backFragment instanceof SearchFragment) || (backFragment instanceof BasketFragment)
                        || (backFragment instanceof StockListFragment) || (backFragment instanceof MainRegisterLoginFragment)
                        || (backFragment instanceof ProfileFragment)) {
                    fragment = new MainFragment();
                } else if (backFragment instanceof StockDetailsFragment) {
                    fragment = new StockDetailsFragment();
                } else if ((backFragment instanceof MyProfileFragment) || (backFragment instanceof MyAddressFragment)
                        || (backFragment instanceof ChangePassFragment) || (backFragment instanceof HistoryFragment)
                        || (backFragment instanceof DownloadsFragment) || (backFragment instanceof FavouritesFragment)
                        || (backFragment instanceof TransActionsFragment) || (backFragment instanceof DiscountsFragment)
                        || (backFragment instanceof MessageFragment) || (backFragment instanceof SupportFragment)) {
                    fragment = new ProfileFragment();
                }
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frm_mainFragment_mainLayout, fragment);
                transaction.commit();
            }
        });
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
    public void onResume() {
        super.onResume();
//        if (isInternetAvailable()) {
////            noInternetDialog.dismiss();
//            setupViews();
//        }

        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        if (checkExit) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            Objects.requireNonNull(getActivity()).finish();
                            System.exit(0);
                        } else if (!checkExit) {
                            showMessage(getResources().getString(R.string.toast_press_back_again));
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkExit = false;
                                }
                            }, 2000);
                            checkExit = true;
                        }
                    }
                }
                return false;
            }
        });
    }

    private boolean isInternetAvailable() {
        boolean result = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getViewContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            } else {
                if (connectivityManager.getActiveNetworkInfo() != null
                        && connectivityManager.getActiveNetworkInfo().isConnected()) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    public void getInit(InitProg initProg) {
        if (initProg != null) {
            try {
                PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                String currentVersionName = packageInfo.versionName;
                String lastVersion = initProg.getApkVersion();
                 url = initProg.getApkUrl();
                if(!currentVersionName.equals(lastVersion)){
                    Toast.makeText(getViewContext(), "در حال دانلود نسخه جدید", Toast.LENGTH_SHORT).show();
                    if(ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSIONDOWNLOAD);
                    }else{
                        downloadNewVersion();
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSIONDOWNLOAD:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    downloadNewVersion();
                }else{
                    showMessage("لطفا اجازه دسترسی به حافظه را به برنامه بدهید");
                }
                return;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.getInit();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    private void downloadNewVersion(){
        WebViewFragment updateApp = new WebViewFragment();
        updateApp.setContext(getViewContext());
        updateApp.execute(url);
    }


}







