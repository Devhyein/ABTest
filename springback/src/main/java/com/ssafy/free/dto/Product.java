package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_no")
    private int productNo; 

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "product_detail")
    private String productDetail;

    @Column(name = "product_image")
    private String productImage;

    public Product() {
    }

    public Product(int product_no, String product_name, int product_price, String product_detail,
            String product_image) {
        this.productNo = product_no;
        this.productName = product_name;
        this.productPrice = product_price;
        this.productDetail = product_detail;
        this.productImage = product_image;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int product_no) {
        this.productNo = product_no;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int product_price) {
        this.productPrice = product_price;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String product_detail) {
        this.productDetail = product_detail;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String product_image) {
        this.productImage = product_image;
    }

    @Override
    public String toString() {
        return "Product [product_detail=" + productDetail + ", product_image=" + productImage + ", product_name="
                + productName + ", product_no=" + productNo + ", product_price=" + productPrice + "]";
    }

  
}
