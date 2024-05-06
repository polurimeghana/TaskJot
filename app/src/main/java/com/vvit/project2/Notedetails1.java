package com.vvit.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Notedetails1 extends AppCompatActivity {
   Button backbtn,sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails1);
        TextView titletv=findViewById(R.id.ttv1);
        String notetitle=getIntent().getStringExtra("title");
        titletv.setText(notetitle);
        TextView desctv=findViewById(R.id.descriptiontv1);
        String desc=getIntent().getStringExtra("Description");
        desctv.setText(desc);
        backbtn=findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharebtn=findViewById(R.id.sharebtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stickytitle=titletv.getText().toString();
                String stickydescription=desctv.getText().toString();
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,stickytitle);
                shareIntent.putExtra(Intent.EXTRA_TEXT,stickydescription);
                startActivity(Intent.createChooser(shareIntent,"SHARE STICKY NOTE"));
            }
        });




    }
}