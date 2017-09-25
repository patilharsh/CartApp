package com.cartapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cartapp.R;
import com.cartapp.fragment.CartFragment;
import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;


public class CartListAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private ArrayList<ShopApiResponse> shopApiResponses;
    private Context context;
    private CartFragment cartFragment;

    public CartListAdapter(Context context, ArrayList<ShopApiResponse> shopApiResponses, CartFragment cartFragment) {
        this.context = context;
        this.shopApiResponses=shopApiResponses;
        this.cartFragment = cartFragment;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        holder.txtProductAmount.setText(context.getString(R.string.price)+"\n"+shopApiResponses.get(position).getPrice());
        holder.txtProductName.setText(shopApiResponses.get(position).getProductname());
        holder.txtVendorAddress.setText(shopApiResponses.get(position).getVendoraddress());
        holder.txtVendorName.setText(shopApiResponses.get(position).getVendorname());
        Glide.with(context)
                .load(shopApiResponses.get(position).getProductImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgProductThumb);
        holder.btnCallVendor.setOnClickListener(new CallVenodrClickListner(shopApiResponses.get(position).getPhoneNumber()));
        holder.btnRemoveFromCart.setOnClickListener(new RemoveFromCartListner(shopApiResponses.get(position)));
    }

    public void removeItem(int position) {
        shopApiResponses.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return shopApiResponses.size();
    }

    private class CallVenodrClickListner implements View.OnClickListener {
        String phoneNumber;
        public CallVenodrClickListner(String phoneNumber) {
            this.phoneNumber=phoneNumber;
        }

        @Override
        public void onClick(View view) {
            cartFragment.callToVendor(phoneNumber);
        }
    }

    private class RemoveFromCartListner implements View.OnClickListener {
        ShopApiResponse shopApiResponse;
        public RemoveFromCartListner(ShopApiResponse shopApiResponse) {
            this.shopApiResponse = shopApiResponse;
        }

        @Override
        public void onClick(View view) {
            cartFragment.RemoveFromCart(shopApiResponse);

        }
    }
}
