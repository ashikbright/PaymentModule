package com.ashik.paymentmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PaymentOut extends AppCompatActivity {
    EditText edtAmountPaid;
    EditText edtDescription;
    EditText edtDate;
    Spinner spinner;
    Button OutSubmit;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_out);

        edtAmountPaid=findViewById(R.id.edtAmtPaid);
        edtDescription=findViewById(R.id.edtOUTDesc);
        edtDate=findViewById(R.id.edtOUTDate);
        spinner=findViewById(R.id.spinnerout);
        OutSubmit=findViewById(R.id.btnOUTSubmit);

        Calendar calendar=Calendar.getInstance();
        final  int year=calendar.get(Calendar.YEAR);
        final  int month=calendar.get(Calendar.MONTH);
        final  int day=calendar.get(Calendar.DAY_OF_MONTH);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        PaymentOut.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        edtDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReference("Projects").child("Payment").child("OUT");
        OutSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payOUT();
                edtAmountPaid.getText().clear();
                edtDescription.getText().clear();
                edtDate.getText().clear();
            }
        });
    }
    private void payOUT(){
        String amountpaid=edtAmountPaid.getText().toString();
        String descriptionOUT=edtDescription.getText().toString();
        String OUTdate=edtDate.getText().toString();
        String categories_list=spinner.getSelectedItem().toString();

        OutPayment outPayment=new OutPayment(amountpaid,descriptionOUT,OUTdate,categories_list);
        databaseReference.push().setValue(outPayment);
        Toast.makeText(this, "Payment Given ...", Toast.LENGTH_SHORT).show();
    }
}