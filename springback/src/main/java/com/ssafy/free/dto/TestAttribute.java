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
@Table(name = "TestAttribute")
public class TestAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_attribute_no")
    private int test_attribute_no; 

    @ManyToOne
    @JoinColumn
    @Parent
    private Test test;

    @Column(name = "attribute_name")
    private String attribute_name;

    @Column(name = "attribute_type")
    private String attribute_type;
    
    @Column(name = "attribute_value")
    private String attribute_value;

    public TestAttribute() {
    }

    public TestAttribute(int test_attribute_no, Test test, String attribute_name, String attribute_type,
            String attribute_value) {
        this.test_attribute_no = test_attribute_no;
        this.test = test;
        this.attribute_name = attribute_name;
        this.attribute_type = attribute_type;
        this.attribute_value = attribute_value;
    }

    public int getTest_attribute_no() {
        return test_attribute_no;
    }

    public void setTest_attribute_no(int test_attribute_no) {
        this.test_attribute_no = test_attribute_no;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
        return "TestAttribute [attribute_name=" + attribute_name + ", attribute_type=" + attribute_type
                + ", attribute_value=" + attribute_value + ", test=" + test + ", test_attribute_no=" + test_attribute_no
                + "]";
    }

    
}