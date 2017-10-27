package com.example.admin.thehealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.admin.thehealthapp.R.id.next;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText email;
Spinner dropdown;
    Spinner sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);

        //get the spinner from the xml.
        dropdown = (Spinner)findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"A+","A-","B+","B-", "O+", "O-","AB+","AB-"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //get the spinner from the xml.
        sex = (Spinner)findViewById(R.id.spinner2);
//create a list of items for the spinner.
        String[] genders = new String[]{"Male","Female","Others"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
//set the spinners adapter to the previously created one.
        sex.setAdapter(adapter2);

        Button next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,Main2Activity.class);
                intent.putExtra("name",username.getText().toString());
                intent.putExtra("email",email.getText().toString());
                intent.putExtra("blood",dropdown.getSelectedItem().toString());
                intent.putExtra("sex",sex.getSelectedItem().toString());
                startActivity(intent);
            }
        });



}}
