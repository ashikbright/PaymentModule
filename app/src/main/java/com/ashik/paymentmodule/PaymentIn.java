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

public class PaymentIn extends AppCompatActivity {
    EditText edtAmountRecieved;
    EditText edtDescription;
    EditText edtDate;
    Spinner spinner;
    Button InSubmit;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference databaseReference;
    long sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_in);

        edtAmountRecieved=findViewById(R.id.edtAmtRcvd);
        edtDescription=findViewById(R.id.edtINDesc);
        edtDate=findViewById(R.id.edtINDate);
        spinner=findViewById(R.id.spinnerpin);
        InSubmit=findViewById(R.id.btnINSubmit);

        Calendar calendar=Calendar.getInstance();
        final  int year=calendar.get(Calendar.YEAR);
        final  int month=calendar.get(Calendar.MONTH);
        final  int day=calendar.get(Calendar.DAY_OF_MONTH);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        PaymentIn.this, new DatePickerDialog.OnDateSetListener() {
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

        databaseReference= FirebaseDatabase.getInstance().getReference("Projects").child("Payment").child("IN");
        InSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payIN();
                edtAmountRecieved.getText().clear();
                edtDescription.getText().clear();
                edtDate.getText().clear();
            }
        });

    }
    private void payIN(){
        String amountrcvd=edtAmountRecieved.getText().toString();
        String descriptionIN=edtDescription.getText().toString();
        String INdate=edtDate.getText().toString();
        String categories_list=spinner.getSelectedItem().toString();

        InPayment inPayment=new InPayment(amountrcvd,descriptionIN,INdate,categories_list);
        databaseReference.push().setValue(inPayment);
        Toast.makeText(this, "Payment Recieved ...", Toast.LENGTH_SHORT).show();
    }
}