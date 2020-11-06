package com.ssafy.free.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userattribute")
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
    private int userNo;

    @Column(name = "admin_no")
    private int adminNo;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "attribute_type")
    private String attributeType;

    @Column(name = "attribute_value")
    private String attributeValue;

    public UserAttribute() {
    }

    public UserAttribute(int user_attribute_no, int userNo, int adminNo, String attributeName, String attributeType,
            String attributeValue) {
        this.user_attribute_no = user_attribute_no;
        this.userNo = userNo;
        this.adminNo = adminNo;
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.attributeValue = attributeValue;
    }

    public int getUser_attribute_no() {
        return user_attribute_no;
    }

    public void setUser_attribute_no(int user_attribute_no) {
        this.user_attribute_no = user_attribute_no;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int adminNo) {
        this.adminNo = adminNo;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        return "UserAttribute [adminNo=" + adminNo + ", attributeName=" + attributeName + ", attributeType="
                + attributeType + ", attributeValue=" + attributeValue + ", userNo=" + userNo + ", user_attribute_no="
                + user_attribute_no + "]";
    }

}
