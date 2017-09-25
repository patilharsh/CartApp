package com.cartapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cartapp.R;
import com.cartapp.activity.MainActivity;
import com.cartapp.adapter.ShopingListAdapter;
import com.cartapp.model.network.ShopApiResponse;
import com.cartapp.presenter.ShopPresenter;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements FillListDelegate,AddToCartListnerDelegate ,AlreadyAdddedDelegate{

    private View view;
    private RecyclerView recyclerViewProduct;
    private ShopingListAdapter shopingListAdapter;
    private MainActivity mActivity;
    ShopPresenter shopPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
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

        shopPresenter= new ShopPresenter(this,this);
        shopPresenter.getProducts();
    }

    @Override
    public void setList(ArrayList<ShopApiResponse> shopApiResponses) {
        if (recyclerViewProduct.getAdapter() == null){
            shopingListAdapter = new ShopingListAdapter(mActivity,shopApiResponses,this);
            recyclerViewProduct.setAdapter(shopingListAdapter);
        }else {
            shopingListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(ShopApiResponse shopApiResponse) {
        shopPresenter.addCart(shopApiResponse);
    }

    @Override
    public void getStatusAdded(Boolean isAdded) {
        if (isAdded){
            Toast.makeText(mActivity,mActivity.getString(R.string.sucess_added_cart),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mActivity,mActivity.getString(R.string.alredy_added_cart),Toast.LENGTH_SHORT).show();
        }
    }
}
