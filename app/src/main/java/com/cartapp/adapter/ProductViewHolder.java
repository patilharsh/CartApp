package com.cartapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cartapp.R;


public class ProductViewHolder extends RecyclerView.ViewHolder {

    ImageView imgProductThumb;
    TextView txtProductName;
    TextView txtProductAmount;
    TextView txtVendorName;
    TextView txtVendorAddress;
    Button btnAddtoCart;


    public ProductViewHolder(View itemView) {
        super(itemView);

        imgProductThumb = (ImageView) itemView.findViewById(R.id.img_product_image);
        txtProductName = (TextView) itemView.findViewById(R.id.txt_pname);
        txtProductAmount = (TextView) itemView.findViewById(R.id.txt_pamount);
        txtVendorName = (TextView) itemView.findViewById(R.id.txt_vname);
        txtVendorAddress = (TextView) itemView.findViewById(R.id.txt_vaddress);
        btnAddtoCart = (Button) itemView.findViewById(R.id.btn_add_cart);
    }
}
