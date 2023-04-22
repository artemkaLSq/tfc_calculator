package com.example.tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout list_linearlay = (LinearLayout)findViewById(R.id.line_sc);
        DbHelper dbHelper = new DbHelper(this);
        try
        { dbHelper.updateDataBase(); }
        catch (IOException mIOException)
        { throw new Error("UnableToUpdateDatabase"); }
        try
        { database = dbHelper.getWritableDatabase(); }
        catch (SQLException mSQLException)
        { throw new Error("mSQLException"); }
        Cursor cursor_data = database.rawQuery(" SELECT name FROM alloy" ,null);
        cursor_data.moveToFirst();
        ArrayList<String> alloys = new ArrayList<String>();
        for (int i = 0; i < cursor_data.getCount(); i++)
        {
            alloys.add(cursor_data.getString(0));
            cursor_data.moveToNext();
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (String alloy : alloys)
        {
            Button button = new Button(this);
            button.setGravity(Gravity.CENTER);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(MainActivity.this, AlloyActivity.class);
                    intent.putExtra("alloy_name", alloy);
                    startActivity(intent);
                }
            });
            button.setText(alloy.replace('_', ' '));
            int picture = getResources().getIdentifier(alloy.toLowerCase(), "drawable", getPackageName());
            button.setCompoundDrawablesWithIntrinsicBounds(picture, 0,0,0);
            list_linearlay.addView(button, layoutParams);
        }

        dbHelper.close();
    }

}