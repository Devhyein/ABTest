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
@Table(name = "testattribute")
public class TestAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_attribute_no")
    private int testAttributeNo; 

    // @ManyToOne
    // @JoinColumn
    // @Parent
    // private Test test;

    @Column(name = "test_no")
    private String testNo;

    @Column(name = "admin_no")
    private String adminNo;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "attribute_type")
    private String attributeType;
    
    @Column(name = "attribute_value")
    private String attributeValue;

    public TestAttribute() {
    }

    public TestAttribute(int testAttributeNo, String testNo, String adminNo, String attributeName, String attributeType,
            String attributeValue) {
        this.testAttributeNo = testAttributeNo;
        this.testNo = testNo;
        this.adminNo = adminNo;
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.attributeValue = attributeValue;
    }

    public int getTestAttributeNo() {
        return testAttributeNo;
    }

    public void setTestAttributeNo(int testAttributeNo) {
        this.testAttributeNo = testAttributeNo;
    }

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
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
        return "TestAttribute [adminNo=" + adminNo + ", attributeName=" + attributeName + ", attributeType="
                + attributeType + ", attributeValue=" + attributeValue + ", testAttributeNo=" + testAttributeNo
                + ", testNo=" + testNo + "]";
    }


}
