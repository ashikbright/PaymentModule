package com.ashik.paymentmodule;

public class InPayment {
    String amtrecieved;
    String descriptionIn;
    String DateIN;
    String c_type;

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
}
