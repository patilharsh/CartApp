package com.cartapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartapp.R;
import com.cartapp.activity.ShoppingNCartActivity;
import com.cartapp.adapter.CartListAdapter;
import com.cartapp.delegate.CallVendorDelegate;
import com.cartapp.delegate.FillListDelegate;
import com.cartapp.delegate.RemoveFromCartDelegate;
import com.cartapp.delegate.RemoveNotifyDelegate;
import com.cartapp.model.network.ShopApiResponse;
import com.cartapp.presenter.CartPresenter;

import java.util.ArrayList;

public class CartFragment extends Fragment implements FillListDelegate, CallVendorDelegate,RemoveFromCartDelegate,RemoveNotifyDelegate {
    private View view;
    private RecyclerView recyclerViewCart;
    private ShoppingNCartActivity mActivity;
    private CartListAdapter cartListAdapter;
    CartPresenter cartPresenter;
    private TextView txtTotalAmount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (ShoppingNCartActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        recyclerViewCart = (RecyclerView) view.findViewById(R.id.recycler_view_cart_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerViewCart.setLayoutManager(mLayoutManager);
        recyclerViewCart.setItemAnimator(new DefaultItemAnimator());
        txtTotalAmount = (TextView)view.findViewById(R.id.txt_total);

        cartPresenter = new CartPresenter(this,this);
        cartPresenter.getCart();

        int result = cartPresenter.TotalAmount();
        txtTotalAmount.setText(getString(R.string.total)+" "+result);
    }

    @Override
    public void setList(ArrayList<ShopApiResponse> shopApiResponses) {
        if (recyclerViewCart.getAdapter() == null){
            cartListAdapter = new CartListAdapter(mActivity,shopApiResponses,this);
            recyclerViewCart.setAdapter(cartListAdapter);
        }else {
            cartListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void callToVendor(String phoneNumber) {
        try {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
        } catch (SecurityException e) {
        }
    }

    @Override
    public void RemoveFromCart(ShopApiResponse shopApiResponse) {
        cartPresenter.RemoveFromCart(shopApiResponse);
    }

    @Override
    public void RemoveStatus(Boolean isRemoved, int position) {

        cartListAdapter.removeItem(position);
        int result = cartPresenter.TotalAmount();
        txtTotalAmount.setText(getString(R.string.total)+": "+result);

        //change the count vlue
    }
}
