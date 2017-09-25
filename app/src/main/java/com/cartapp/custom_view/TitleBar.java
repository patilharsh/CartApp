package com.cartapp.custom_view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cartapp.R;


public class TitleBar extends RelativeLayout {

    private Context context;

    public static final int BG_ITEM_PRIMARY = 1111;
    public static final int BG_ITEM_PRIMARY_DARK = 1112;


    public static final int TITLE_COLOR_TYPE_BLACK = 1001;
    public static final int TITLE_COLOR_TYPE_WHITE = 1002;


    private RelativeLayout rlContainer;



    private TextView title;


    public TitleBar(Context context) {
        super(context);
        this.context = context;

        initUI(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initUI(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initUI(context);
    }


    /**
     * init UI
     *
     * @param context
     */
    private void initUI(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.titlebar, this, true);

        setTitleBarResources();

    }

    /**
     * Method to set Base UI Elements of Title Bar
     */
    private void setTitleBarResources() {

        rlContainer = (RelativeLayout) findViewById(R.id.rl_toolbar_container);


        title = (TextView) findViewById(R.id.title_big);


    }


    /**
     * This method will set background resource of titlebar
     *
     * @param backgroundType
     */
    public void setBackgroundResourceType(int backgroundType) {

        switch (backgroundType) {

            case BG_ITEM_PRIMARY:
                rlContainer.setBackgroundResource(R.color.colorPrimary);
                break;

            case BG_ITEM_PRIMARY_DARK:
                rlContainer.setBackgroundResource(R.color.colorPrimaryDark);
                break;


        }
    }


    /**
     * Sets title color as selected
     *
     * @param colorType
     */
    public void setTitleColor(int colorType) {

        switch (colorType) {
            case TITLE_COLOR_TYPE_BLACK:
                title.setTextColor(ContextCompat.getColor(context, R.color.title_text_black));
                break;

            case TITLE_COLOR_TYPE_WHITE:
                title.setTextColor(ContextCompat.getColor(context, R.color.title_text_white));
                break;
        }
    }


    /**
     * Method to set Title
     *
     * @param arrInfo
     */
    public void setTitleValues(String... arrInfo) {

                title.setVisibility(View.VISIBLE);
                title.setText(arrInfo[0]);
    }


}