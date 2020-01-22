package com.jk.loginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name,phoneNumber,emailAddress, password;
    private Button submit;
    private MySqliteOpenHelper mySqliteOpenHelper;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit=findViewById(R.id.submitButton);
        name=findViewById(R.id. name);
        phoneNumber=findViewById(R.id.phoneNumber);
        emailAddress=findViewById(R.id.emailAddress);
        password=findViewById(R.id.password);


        mySqliteOpenHelper=new MySqliteOpenHelper(this, " ", null,1);

        if (getIntent().hasExtra("Edit")){
            EmployModel employModel =mySqliteOpenHelper.getEmplyee(Integer.parseInt(
                    getIntent().getStringExtra("Edit")));
            name.setText(employModel.getName());
            emailAddress.setText(employModel.getEmailAddress());
            phoneNumber.setText(employModel.getPhoneNumber());
        }
        
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().length()==0){
                    name.setError(" empty field!");
                }else if (phoneNumber.getText().toString().length()==0){
                    phoneNumber.setError("empty field!");
                }else if (emailAddress.getText().toString().length()==0){
                    emailAddress.setError("empty field!");
                }else if (password.getText().toString().length()==0){
                    password.setError("empty field!");
                }else {
                    if (getIntent().hasExtra("Edit"))
                    {

                        mySqliteOpenHelper.updateEmploye(Integer.parseInt(getIntent().getStringExtra("Edit")),
                                name.getText().toString(),
                                phoneNumber.getText().toString(),
                                emailAddress.getText().toString());
                    }
                    else {
                        mySqliteOpenHelper.addEmployee(
                                name.getText().toString(),
                                emailAddress.getText().toString(),
                                phoneNumber.getText().toString(),
                                password.getText().toString());

                        Toast.makeText(MainActivity.this, "Info Added Successfuly", Toast.LENGTH_SHORT).show();
                    }startActivity(new Intent(MainActivity.this,EmployList.class));
                }
            }
        });

    }
}
