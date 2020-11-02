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

    @Column(name = "test_no")
    private int testNo;

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

    public Product(int productNo, int testNo, String productName, int productPrice, String productDetail,
            String productImage) {
        this.productNo = productNo;
        this.testNo = testNo;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetail = productDetail;
        this.productImage = productImage;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Product [ productDetail=" + productDetail + ", productImage=" + productImage
                + ", productName=" + productName + ", productNo=" + productNo + ", productPrice=" + productPrice
                + ", testNo=" + testNo + "]";
    }

  
  
}
