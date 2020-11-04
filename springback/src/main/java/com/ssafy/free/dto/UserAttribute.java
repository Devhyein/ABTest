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
@Table(name = "userattribute")
public class UserAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_attribute_no")
    private int userAttributeNo;

    // @ManyToOne
    // @JoinColumn
    // @Parent
    // private User user;

    @Column(name = "user_no")
    private int userNo;

    @Column(name = "page_type")
    private String pageType;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "attribute_type")
    private String attributeType;

    @Column(name = "attribute_value")
    private String attributeValue;

    public UserAttribute() {
    }

    public UserAttribute(int userAttributeNo, int userNo, String pageType, String attributeName, String attributeType,
            String attributeValue) {
        this.userAttributeNo = userAttributeNo;
        this.userNo = userNo;
        this.pageType = pageType;
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.attributeValue = attributeValue;
    }

    public int getUserAttributeNo() {
        return userAttributeNo;
    }

    public void setUserAttributeNo(int userAttributeNo) {
        this.userAttributeNo = userAttributeNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
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
        return "UserAttribute [attributeName=" + attributeName + ", attributeType=" + attributeType
                + ", attributeValue=" + attributeValue + ", pageType=" + pageType + ", userAttributeNo="
                + userAttributeNo + ", userNo=" + userNo + "]";
    }

}
