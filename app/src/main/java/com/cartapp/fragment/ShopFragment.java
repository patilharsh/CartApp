package com.cartapp.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cartapp.R;
import com.cartapp.activity.ShoppingNCartActivity;
import com.cartapp.adapter.ShopingListAdapter;
import com.cartapp.application.ApplicationController;
import com.cartapp.delegate.AddToCartListnerDelegate;
import com.cartapp.delegate.AlreadyAdddedDelegate;
import com.cartapp.delegate.FillListDelegate;
import com.cartapp.model.network.ShopApiResponse;
import com.cartapp.presenter.ShopPresenter;
import com.cartapp.utils.ConnectionReceiver;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements FillListDelegate, AddToCartListnerDelegate, AlreadyAdddedDelegate, ConnectionReceiver.ConnectionReceiverListener {

    private View view;
    private RecyclerView recyclerViewProduct;
    private ShopingListAdapter shopingListAdapter;
    private ShoppingNCartActivity mActivity;
    ShopPresenter shopPresenter;
    private TextView txtNoNetwork;
    private ProgressDialog progressDoalog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (ShoppingNCartActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_products, container, false);
        initUI();
        return view;
    }


    private void initUI() {
        recyclerViewProduct = (RecyclerView) view.findViewById(R.id.recycler_view_products);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mActivity, 2);
        recyclerViewProduct.setLayoutManager(mLayoutManager);
        recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
        txtNoNetwork = (TextView) view.findViewById(R.id.txt_no_network);
        progressDoalog = new ProgressDialog(mActivity);
        progressDoalog.setMessage("Its loading....");
        shopPresenter = new ShopPresenter(this, this);
        checkConnection();
    }

    @Override
    public void setList(ArrayList<ShopApiResponse> shopApiResponses) {
        if(progressDoalog !=null && progressDoalog.isShowing()){
            progressDoalog.dismiss();
        }
        if (recyclerViewProduct.getAdapter() == null) {
            shopingListAdapter = new ShopingListAdapter(mActivity, shopApiResponses, this);
            recyclerViewProduct.setAdapter(shopingListAdapter);
        } else {
            shopingListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(ShopApiResponse shopApiResponse) {
        shopPresenter.addCart(shopApiResponse);
    }

    @Override
    public void getStatusAdded(Boolean isAdded) {
        if (isAdded) {
            Toast.makeText(mActivity, mActivity.getString(R.string.sucess_added_cart), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, mActivity.getString(R.string.alredy_added_cart), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            txtNoNetwork.setVisibility(View.VISIBLE);
            recyclerViewProduct.setVisibility(View.INVISIBLE);
        } else {
            txtNoNetwork.setVisibility(View.GONE);
            recyclerViewProduct.setVisibility(View.VISIBLE);
            shopPresenter.getProducts();
            if (progressDoalog != null) {
                progressDoalog.show();
            }
        }
    }

    private void checkConnection() {
        boolean isConnected = ConnectionReceiver.isConnected();
        if (!isConnected) {
            recyclerViewProduct.setVisibility(View.INVISIBLE);
            txtNoNetwork.setVisibility(View.VISIBLE);
        } else {
            recyclerViewProduct.setVisibility(View.VISIBLE);
            shopPresenter.getProducts();
            txtNoNetwork.setVisibility(View.GONE);
            if (progressDoalog != null) {
                progressDoalog.show();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(progressDoalog !=null && progressDoalog.isShowing()){
            progressDoalog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ApplicationController.getInstance().setConnectionListener(this);
    }
}
