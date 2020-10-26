package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parent;

@Entity
@Table(name = "UserAttribute")
public class UserAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_attribute_no")
    private int user_attribute_no; 

    // @ManyToOne
    // @JoinColumn
    // @Parent
    // private User user;

    @Column(name = "user_no")
    private int user_no;

    @Column(name = "attribute_name")
    private String attribute_name;

    @Column(name = "attribute_type")
    private String attribute_type;
    
    @Column(name = "attribute_value")
    private String attribute_value;

    public UserAttribute() {
    }

    public UserAttribute(int user_attribute_no, int user_no, String attribute_name, String attribute_type,
            String attribute_value) {
        this.user_attribute_no = user_attribute_no;
        this.user_no = user_no;
        this.attribute_name = attribute_name;
        this.attribute_type = attribute_type;
        this.attribute_value = attribute_value;
    }

    public UserAttribute(int user_no, String attribute_name, String attribute_type, String attribute_value) {
        this.user_no = user_no;
        this.attribute_name = attribute_name;
        this.attribute_type = attribute_type;
        this.attribute_value = attribute_value;
    }

    public int getUser_attribute_no() {
        return user_attribute_no;
    }

    public void setUser_attribute_no(int user_attribute_no) {
        this.user_attribute_no = user_attribute_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public String getAttribute_type() {
        return attribute_type;
    }

    public void setAttribute_type(String attribute_type) {
        this.attribute_type = attribute_type;
    }

    public String getAttribute_value() {
        return attribute_value;
    }

    public void setAttribute_value(String attribute_value) {
        this.attribute_value = attribute_value;
    }

    @Override
    public String toString() {
        return "UserAttribute [attribute_name=" + attribute_name + ", attribute_type=" + attribute_type
                + ", attribute_value=" + attribute_value + ", user_attribute_no=" + user_attribute_no + ", user_no="
                + user_no + "]";
    }

}
