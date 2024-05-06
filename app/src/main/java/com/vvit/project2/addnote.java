package com.vvit.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class addnote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        EditText titleinput=findViewById(R.id.titleinput);
        EditText descriptioninput=findViewById(R.id.descriptioninput);
        MaterialButton saveBtn=findViewById(R.id.savebtn);
        Realm.init(getApplicationContext());
        Realm realm= Realm.getDefaultInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleinput.getText().toString();
                String description=descriptioninput.getText().toString();
                long createdTime=System.currentTimeMillis();
                realm.beginTransaction();
                Note1 note=realm.createObject(Note1.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note Saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}