package com.ashik.paymentmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentAdapterOut extends RecyclerView.Adapter<PaymentAdapterOut.MyViewHolder>  {
    Context context;
    ArrayList<OutPayment> outlist;

    public PaymentAdapterOut(Context context, ArrayList<OutPayment> outlist) {
        this.context = context;
        this.outlist = outlist;
    }
    @NonNull
    @Override
    public PaymentAdapterOut.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.payment_details_out,parent,false);
        return new PaymentAdapterOut.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull PaymentAdapterOut.MyViewHolder holder, int position) {
        OutPayment outPayment=outlist.get(position);
        holder.date.setText(outPayment.getDateOUT());
        holder.desc.setText(outPayment.getDescriptionOut());
        holder.categ2.setText(outPayment.getC_type());
        holder.out.setText(outPayment.getAmtPaid());
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return outlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date,desc,categ2,out;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.txtdateout);
            desc=itemView.findViewById(R.id.txtdescout);
            categ2=itemView.findViewById(R.id.txtcout);
            out=itemView.findViewById(R.id.txtamtout);
        }
    }
}
