package com.cartapp.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.cartapp.R;
import com.cartapp.custom_view.TabBarDelegate;
import com.cartapp.custom_view.TabBarView;
import com.cartapp.custom_view.TitleBar;
import com.cartapp.fragment.CartFragment;
import com.cartapp.fragment.ShopFragment;
import com.cartapp.utils.AppConstants;

public class MainActivity extends Activity implements TabBarDelegate {

    private TitleBar titleBar;
    private TabBarView tabBarView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        ShopFragment shopFragment = new ShopFragment();
        fragmentTransaction.replace(R.id.frame_content, shopFragment);
        fragmentTransaction.commit();
        initUI();
    }
    public void initUI(){

        tabBarView = (TabBarView) findViewById(R.id.tab_bar);
        tabBarView.setActivityForView(MainActivity.this);
        tabBarView.setCallbackListener(this);
        tabBarView.setTabBarSelection(AppConstants.MENU_PRODUCTS);
        setTitleBar(getString(R.string.shop));
    }
    @Override
    public void onTabOptionSelected(int menuType) {
        switch (menuType) {
            case AppConstants.MENU_PRODUCTS:
                setTitleBar(getString(R.string.shop));
                ShopFragment shopFragment =new ShopFragment();
                replaceFragment(shopFragment);
                break;
            case AppConstants.MENU_CART:
                setTitleBar(getString(R.string.cart));
                CartFragment cartFragment = new CartFragment();
                replaceFragment(cartFragment);
                break;

        }
    }

    public void setTitleBar(String title){
        titleBar = (TitleBar) findViewById(R.id.lay_toolbar_title);
        titleBar.setTitleColor(TitleBar.TITLE_COLOR_TYPE_WHITE);
        titleBar.setBackgroundResourceType(TitleBar.BG_ITEM_PRIMARY);
        titleBar.setTitleValues(title);
    }

    public void replaceFragment(Fragment mFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, mFragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
