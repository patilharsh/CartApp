package com.cartapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cartapp.R;
import com.cartapp.fragment.ShopFragment;
import com.cartapp.model.network.ShopApiResponse;

import java.util.ArrayList;


public class ShopingListAdapter extends RecyclerView.Adapter<ProductViewHolder> {


    private ArrayList<ShopApiResponse> shopApiResponses;
    private Context context;
    private ShopFragment shopFragment;

    public ShopingListAdapter(Context context, ArrayList<ShopApiResponse> shopApiResponses, ShopFragment shopFragment) {
        this.context = context;
        this.shopApiResponses=shopApiResponses;
        this.shopFragment=shopFragment;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.txtProductAmount.setText(shopApiResponses.get(position).getPrice());
        holder.txtProductName.setText(shopApiResponses.get(position).getProductname());
        holder.txtVendorAddress.setText(shopApiResponses.get(position).getVendoraddress());
        holder.txtVendorName.setText(shopApiResponses.get(position).getVendorname());
        Glide.with(context)
                .load(shopApiResponses.get(position).getProductImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgProductThumb);
        holder.btnAddtoCart.setOnClickListener(new AddToCartListner(shopApiResponses.get(position)));

    }


    private class AddToCartListner implements View.OnClickListener{
        ShopApiResponse shopApiResponse;
        public AddToCartListner(ShopApiResponse shopApiResponse) {
            this.shopApiResponse=shopApiResponse;
        }

        @Override
        public void onClick(View view) {

            shopFragment.onClick(shopApiResponse);
        }
    }

    @Override
    public int getItemCount() {
        return shopApiResponses.size();
    }

}
