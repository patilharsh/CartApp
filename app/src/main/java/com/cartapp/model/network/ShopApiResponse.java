package com.cartapp.model.network;

import java.io.Serializable;
import java.util.ArrayList;



public class ShopApiResponse implements Serializable {


    private String productname;
    private String price;
    private String vendorname;
    private String vendoraddress;
    private String productImg;
    private ArrayList<String> productGallery;
    private String phoneNumber;

    public ShopApiResponse(String productname, String price, String vendorname, String vendoraddress, String productImg, ArrayList<String> productGallery, String phoneNumber) {
        this.productname = productname;
        this.price = price;
        this.vendorname = vendorname;
        this.vendoraddress = vendoraddress;
        this.productImg = productImg;
        this.productGallery = productGallery;
        this.phoneNumber = phoneNumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public ArrayList<String> getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(ArrayList<String> productGallery) {
        this.productGallery = productGallery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShopApiResponse other = (ShopApiResponse) obj;
        if (productname == null) {
            if (other.productname != null)
                return false;
        } else if (!productname.equals(other.productname))
            return false;
        return true;
    }
}
