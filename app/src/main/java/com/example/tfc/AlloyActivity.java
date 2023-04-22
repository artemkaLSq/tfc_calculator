package com.example.tfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class AlloyActivity extends AppCompatActivity {

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alloy);
        DbHelper dbHelper = new DbHelper(this);
        try
        { dbHelper.updateDataBase(); }
        catch (IOException mIOException)
        { throw new Error("UnableToUpdateDatabase"); }
        try
        { database = dbHelper.getWritableDatabase(); }
        catch (SQLException mSQLException)
        { throw new Error("mSQLException"); }
        String alloy_name = getIntent().getStringExtra("alloy_name");
        TextView alloy_nameView = findViewById(R.id.alloy_name);
        alloy_nameView.setText(alloy_name.replace('_', ' '));
        String query = String.format("SELECT * FROM alloy where upper(name) = '%s'", alloy_name.toUpperCase());
        Cursor cursor_data = database.rawQuery(query, null);
        cursor_data.moveToFirst();
        ArrayList<Metal> metalsList = new ArrayList<Metal>();
        for (int i = 1; i <= 4; i++)
        {
            String metal_name = cursor_data.getString(i);
            if (metal_name != null)
            {
                float partLow = cursor_data.getFloat(i*2+3);
                float partHigh = cursor_data.getFloat(i*2+4);
                int picture = getResources().getIdentifier(metal_name.toLowerCase(), "drawable", getPackageName());
                metalsList.add(new Metal(metal_name, picture, partLow, partHigh));
            }
        }

        RecyclerView recyclerView = findViewById(R.id.list);
        MetalAdapter adapter = new MetalAdapter(this, metalsList);
        recyclerView.setAdapter(adapter);

        TextView metalSum = findViewById(R.id.metal_sum);
        Button calcButt = findViewById(R.id.calcButt);
        calcButt.setOnClickListener(it ->{
            float sum = 0;
            for (Metal metal : metalsList)
                sum += metal.amount;

            boolean ok = true;
            String output = "";
            if (sum != 0) {
                for (Metal metal : metalsList)
                {
                    float prop = metal.amount/sum;
                    if (prop < metal.partLow || prop > metal.partHigh) {
                        ok = false;
                        output += String.format("%s is %.2f %%\n", metal.getName(), prop*100);
                    }
                }
            }
            else
                ok = false;
            if (ok) {
                metalSum.setTextColor(Color.rgb(0,255,0));
                metalSum.setText(String.format("OK. %d units of metal", (int) sum));
            }
            else {
                metalSum.setTextColor(Color.rgb(255,0,0));
                metalSum.setText(output);
            }
        });
    }
}