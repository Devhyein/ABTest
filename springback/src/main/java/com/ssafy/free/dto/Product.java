package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_no")
    private int product_no; 

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_price")
    private int product_price;

    @Column(name = "product_detail")
    private String product_detail;

    @Column(name = "product_image")
    private String product_image;

    public Product() {
    }

    public Product(int product_no, String product_name, int product_price, String product_detail,
            String product_image) {
        this.product_no = product_no;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_detail = product_detail;
        this.product_image = product_image;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    @Override
    public String toString() {
        return "Product [product_detail=" + product_detail + ", product_image=" + product_image + ", product_name="
                + product_name + ", product_no=" + product_no + ", product_price=" + product_price + "]";
    }

  
}
