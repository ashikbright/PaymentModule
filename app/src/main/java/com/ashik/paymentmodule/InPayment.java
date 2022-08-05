package com.ashik.paymentmodule;

import com.google.firebase.database.Exclude;

public class InPayment {
    String amtrecieved;
    String descriptionIn;
    String DateIN;
    String c_type;
    private  String key;

    public InPayment(){}

    public InPayment(String amtrecieved, String descriptionIn, String dateIN, String c_type) {
        this.amtrecieved = amtrecieved;
        this.descriptionIn = descriptionIn;
        DateIN = dateIN;
        this.c_type = c_type;
    }

    public String getAmtrecieved() {
        return amtrecieved;
    }

    public String getDescriptionIn() {
        return descriptionIn;
    }

    public String getDateIN() {
        return DateIN;
    }

    public String getC_type() {
        return c_type;
    }

    public void setAmtrecieved(String amtrecieved) {
        this.amtrecieved = amtrecieved;
    }

    public void setDescriptionIn(String descriptionIn) {
        this.descriptionIn = descriptionIn;
    }

    public void setDateIN(String dateIN) {
        DateIN = dateIN;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}


