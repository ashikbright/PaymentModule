package com.ashik.paymentmodule;

public class OutPayment {
    String amtPaid;
    String descriptionOut;
    String DateOUT;
    String c_type;

    public OutPayment(String amtPaid, String descriptionOut, String dateOUT, String c_type) {
        this.amtPaid = amtPaid;
        this.descriptionOut = descriptionOut;
        DateOUT = dateOUT;
        this.c_type = c_type;
    }

    public String getAmtPaid() {
        return amtPaid;
    }

    public String getDescriptionOut() {
        return descriptionOut;
    }

    public String getDateOUT() {
        return DateOUT;
    }

    public String getC_type() {
        return c_type;
    }
}
