package com.example.cecildot.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText id,name,course;
    Button save,fetch,search;
    TextView tv;
    GridView dataview;
    ArrayAdapter adapter;
    Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new Helper(this);
        id = (EditText) findViewById(R.id.edtid);
        name = (EditText) findViewById(R.id.edtname);
        course = (EditText) findViewById(R.id.edtcourse);
        tv = (TextView) findViewById(R.id.tv);
        save = (Button) findViewById(R.id.btnsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();

                String userid = id.getText().toString();
                String usercourse = course.getText().toString();
                String username = name.getText().toString();
                String msg = helper.saveData(userid,username,usercourse);
                tv.setText(""+msg);
            }
        });
        search = (Button) findViewById(R.id.btnsearch);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        dataview = (GridView) findViewById(R.id.mygrid);
        dataview.setAdapter(adapter);
        fetch = (Button) findViewById(R.id.btnfetch);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = helper.fetchData();
                cursor.moveToFirst();
                while(cursor.moveToNext()){
                    adapter.add(cursor.getInt(0));
                    adapter.add(cursor.getString(1));
                    adapter.add(cursor.getString(2));
                    adapter.notifyDataSetChanged();

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = id.getText().toString();
                Helper helper = new Helper(MainActivity.this);
                Cursor cursor = helper.searchitem(uid);
                cursor.moveToFirst();
                while(cursor.moveToNext()){
                    adapter.add(cursor.getInt(0));
                    adapter.add(cursor.getString(1));
                    adapter.add(cursor.getString(2));
                    adapter.notifyDataSetChanged();
            }


            }
        });


    }
}
