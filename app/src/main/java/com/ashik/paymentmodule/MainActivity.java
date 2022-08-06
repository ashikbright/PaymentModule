package com.ashik.paymentmodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    private PaymentAdapter paymentAdapter;
    private PaymentAdapterOut paymentAdapterOut;
    ArrayList<InPayment> inlist;
    ArrayList<OutPayment> outlist;
    private ValueEventListener mDBListner;
    private DatabaseReference root1 = FirebaseDatabase.getInstance().getReference("Projects").child("Payment").child("IN");
    private DatabaseReference root2 = FirebaseDatabase.getInstance().getReference("Projects").child("Payment").child("OUT");
    Button btnin,btnout;
    TextView totalin,totalout,balamt;
    Integer totalInAmt=0;
    Integer totalOutAmt=0;
    Integer BalanceAmount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnin=findViewById(R.id.btnpayin);
        btnout=findViewById(R.id.btnpayout);
        totalin=findViewById(R.id.txttotin);
        totalout=findViewById(R.id.txttotalout);
        balamt=findViewById(R.id.txtbalamt);
        RecyclerView recyclerViewin = findViewById(R.id.recyclerin);
        recyclerViewin.setHasFixedSize(true);
        recyclerViewin.setLayoutManager(new LinearLayoutManager(this));
        inlist=new ArrayList<>();
        paymentAdapter=new PaymentAdapter(this,inlist);//,outlist);
//        paymentAdapterOut=new PaymentAdapterOut(this,outlist);
        recyclerViewin.setAdapter(paymentAdapter);

        RecyclerView recyclerViewout = findViewById(R.id.recyclerout);
        recyclerViewout.setHasFixedSize(true);
        recyclerViewout.setLayoutManager(new LinearLayoutManager(this));
        outlist=new ArrayList<>();
//        paymentAdapterOut=new PaymentAdapterOut(this,inlist);//,outlist);
        paymentAdapterOut=new PaymentAdapterOut(this,outlist);
        recyclerViewout.setAdapter(paymentAdapterOut);


        mDBListner=root1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inlist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    InPayment inPayment = dataSnapshot.getValue(InPayment.class);
                    inPayment.setKey(dataSnapshot.getKey());

                    int cost= Integer.parseInt(inPayment.getAmtrecieved());
                    totalInAmt += cost;
                    totalin.setText(new StringBuilder().append("₹").append(valueOf(totalInAmt)).toString());

                    inlist.add(inPayment);
                }
                paymentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed To Load!!!", Toast.LENGTH_SHORT).show();
            }
        });

        mDBListner=root2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                outlist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OutPayment outPayment = dataSnapshot.getValue(OutPayment.class);
                    outPayment.setKey(dataSnapshot.getKey());

                    int cost= Integer.parseInt(outPayment.getAmtPaid());
                    totalOutAmt += cost;
                    totalout.setText(new StringBuilder().append("₹").append(valueOf(totalOutAmt)).toString());
//                    String in= totalin.getText().toString();
//                    int amtin=Integer.parseInt(in);
//                    BalanceAmount=amtin-totalOutAmt;
//                    balamt.setText((new StringBuilder().append("₹").append(valueOf(BalanceAmount)).toString()));

                    outlist.add(outPayment);
                }
                paymentAdapterOut.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PaymentIn.class);
                startActivity(intent);
            }
        });
        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PaymentOut.class);
                startActivity(intent);
            }
        });


    }
}