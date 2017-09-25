package com.cartapp.custom_view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cartapp.R;
import com.cartapp.utils.AppConstants;


public class TabBarView extends LinearLayout {

    private Context context;
    private Activity activity;
    private TabBarDelegate tabBarDelegate;

    private RelativeLayout rlProduct;
    private RelativeLayout rlCart;

    private int optionSelected = -1;

    public TabBarView(Context context) {
        super(context);
        this.context = context;
        initUI(context);
    }

    public TabBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initUI(context);
    }

    public TabBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initUI(context);
    }

    /**
     * sets activity reference for Tab Selection view
     *
     * @param activity
     */
    public void setActivityForView(Activity activity) {
        this.activity = activity;
    }

    /**
     * Sets callback listener for view
     *
     * @param tabBarDelegate
     */
    public void setCallbackListener(TabBarDelegate tabBarDelegate) {
        this.tabBarDelegate = tabBarDelegate;
    }

    public int getOptionSelected() {
        return optionSelected;
    }

    public void setOptionSelected(int optionSelected) {
        this.optionSelected = optionSelected;
    }


    /**
     * init UI
     *
     * @param context
     */
    private void initUI(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.tabbar, this, true);

        setResources();
    }

    /**
     * Method to set Base UI Elements of Tab Selection View
     */
    private void setResources() {

        rlProduct = findViewById(R.id.rl_product);
        rlProduct.setOnClickListener(new OnClickListener(AppConstants.MENU_PRODUCTS));

        rlCart = findViewById(R.id.rl_cart);
        rlCart.setOnClickListener(new OnClickListener(AppConstants.MENU_CART));

    }


    private class OnClickListener implements View.OnClickListener {

        private int menuTypeSelected;

        public OnClickListener(int menuTypeSelected) {
            this.menuTypeSelected = menuTypeSelected;
        }

        @Override
        public void onClick(View view) {

            setTabBarSelection(menuTypeSelected);

            if (tabBarDelegate != null) {
                tabBarDelegate.onTabOptionSelected(menuTypeSelected);
            }

        }

    }


    public void setTabBarSelection(int tabSelected) {

        setOptionSelected(tabSelected);

        switch (tabSelected) {
            case AppConstants.MENU_PRODUCTS:
                rlCart.setSelected(false);
                rlProduct.setSelected(true);

                break;
            case AppConstants.MENU_CART:
                rlProduct.setSelected(false);
                rlCart.setSelected(true);
                break;
        }
    }



}