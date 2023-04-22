package com.example.tfc;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MetalAdapter extends RecyclerView.Adapter<MetalAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Metal> Metals;

    MetalAdapter(Context context, List<Metal> Metals) {
        this.Metals = Metals;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public MetalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.metal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MetalAdapter.ViewHolder holder, int position) {
        Metal metal = Metals.get(position);
        holder.imageView.setImageResource(metal.getImageResource());
        String props = String.format(" (%d - %d %%)", (int) (metal.partLow*100), (int) (metal.partHigh*100));
        holder.nameView.setText(metal.getName().replace('_', ' ') + props);
        holder.amountView.setText("0");

        holder.plus5view.setOnClickListener(it -> {
            String currText = holder.five_amountView.getText().toString();
            currText = (currText.length() == 0) ? "0" : currText;
            int newText = Integer.parseInt(currText)+1;
            metal.amount = newText;
            holder.five_amountView.setText(Integer.toString(newText));
        });
        holder.plus10view.setOnClickListener(it -> {
            String currText = holder.ten_amountView.getText().toString();
            currText = (currText.length() == 0) ? "0" : currText;
            int newText = Integer.parseInt(currText)+1;
            metal.amount = newText;
            holder.ten_amountView.setText(Integer.toString(newText));
        });
        holder.plus15view.setOnClickListener(it -> {
            String currText = holder.fifteen_amountView.getText().toString();
            currText = (currText.length() == 0) ? "0" : currText;
            int newText = Integer.parseInt(currText)+1;
            metal.amount = newText;
            holder.fifteen_amountView.setText(Integer.toString(newText));
        });
        holder.plus20view.setOnClickListener(it -> {
            String currText = holder.twenty_amountView.getText().toString();
            currText = (currText.length() == 0) ? "0" : currText;
            int newText = Integer.parseInt(currText)+1;
            metal.amount = newText;
            holder.twenty_amountView.setText(Integer.toString(newText));
        });

        holder.five_amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int metal_sum = reCount(holder);
                holder.amountView.setText(Integer.toString(metal_sum));
                metal.amount = metal_sum;
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        holder.ten_amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int metal_sum = reCount(holder);
                holder.amountView.setText(Integer.toString(metal_sum));
                metal.amount = metal_sum;
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        holder.fifteen_amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int metal_sum = reCount(holder);
                holder.amountView.setText(Integer.toString(metal_sum));
                metal.amount = metal_sum;
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        holder.twenty_amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int metal_sum = reCount(holder);
                holder.amountView.setText(Integer.toString(metal_sum));
                metal.amount = metal_sum;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private int reCount(MetalAdapter.ViewHolder holder)
    {
        String currText = holder.amountView.getText().toString();
        ArrayList<String> mults = new ArrayList<String>();
        mults.add(holder.five_amountView.getText().toString());
        mults.add(holder.ten_amountView.getText().toString());
        mults.add(holder.fifteen_amountView.getText().toString());
        mults.add(holder.twenty_amountView.getText().toString());
        int newText = 0;
        for (int i = 0; i < 4; i++){
            if (mults.get(i).length() != 0)
                newText += Integer.parseInt(mults.get(i))*(i+1)*5;
        }
        return newText;
    }

    @Override
    public int getItemCount() {
        return Metals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final TextView amountView;
        final Button plus5view;
        final Button plus10view;
        final Button plus15view;
        final Button plus20view;
        final EditText five_amountView;
        final EditText ten_amountView;
        final EditText fifteen_amountView;
        final EditText twenty_amountView;

        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.metal_image);
            nameView = view.findViewById(R.id.metal_name);
            amountView = view.findViewById(R.id.metal_amount);
            plus5view = view.findViewById(R.id.plus5);
            plus10view = view.findViewById(R.id.plus10);
            plus15view = view.findViewById(R.id.plus15);
            plus20view = view.findViewById(R.id.plus20);
            five_amountView = view.findViewById(R.id.five_amount);
            ten_amountView = view.findViewById(R.id.ten_amount);
            fifteen_amountView = view.findViewById(R.id.fifteen_amount);
            twenty_amountView = view.findViewById(R.id.twenty_amount);
        }
    }
}
