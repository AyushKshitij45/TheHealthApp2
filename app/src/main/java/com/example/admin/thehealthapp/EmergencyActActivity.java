package com.example.admin.thehealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class EmergencyActActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergengyact);

        FrameLayout stranger=(FrameLayout)(findViewById(R.id.stranger));

        stranger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(EmergencyActActivity.this,EmargencyResponseActivity.class);
                startActivity(intent);
            }
        });

        FrameLayout self=(FrameLayout)(findViewById(R.id.self));

        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(EmergencyActActivity.this,EmargencyResponseActivity.class);
                startActivity(intent);
            }
        });
    }
}
