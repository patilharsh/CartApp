package com.cartapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartapp.R;

/**
 * Created by Harshal on 24/09/17.
 */

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView imgProductThumb;
    TextView txtProductName;
    TextView txtProductAmount;
    TextView txtVendorName;
    TextView txtVendorAddress;
    Button btnRemoveFromCart;
    Button btnCallVendor;

    public CartViewHolder(View itemView) {
        super(itemView);

        imgProductThumb = (ImageView) itemView.findViewById(R.id.img_product_image);
        txtProductName = (TextView) itemView.findViewById(R.id.txt_pname);
        txtProductAmount = (TextView) itemView.findViewById(R.id.txt_pamount);
        txtVendorName = (TextView) itemView.findViewById(R.id.txt_vname);
        txtVendorAddress = (TextView) itemView.findViewById(R.id.txt_vaddress);
        btnRemoveFromCart = (Button) itemView.findViewById(R.id.btn_remove_from_cart);
        btnCallVendor = (Button) itemView.findViewById(R.id.btn_call_vendor);
    }
}
