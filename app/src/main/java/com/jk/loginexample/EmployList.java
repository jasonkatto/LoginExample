package com.jk.loginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EmployList extends AppCompatActivity {
    ListView listView;
    MySqliteOpenHelper mySqliteOpenHelper ;
    ArrayList<EmployModel> arrayList;
    EmployListAdapter employListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_list);
        listView=findViewById(R.id.listView);

        mySqliteOpenHelper = new MySqliteOpenHelper(this, " ", null, 1);
        arrayList= new ArrayList<>();
        arrayList= mySqliteOpenHelper.getEmployess();
        employListAdapter = new EmployListAdapter(arrayList, this);
        listView.setAdapter(employListAdapter);





    }
}
