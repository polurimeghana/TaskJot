package com.vvit.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Sticky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        MaterialButton addNoteBtn=findViewById(R.id.st_addnewnotebtn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sticky.this,addnote.class));

            }
        });
        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Note1> noteList=realm.where(Note1.class).findAll();
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Myadapter myAdapter=new Myadapter(getApplicationContext(),noteList);
        recyclerView.setAdapter(myAdapter);
        noteList.addChangeListener(new RealmChangeListener<RealmResults<Note1>>() {
            @Override
            public void onChange(RealmResults<Note1> notes) {myAdapter.notifyDataSetChanged();
            }
        });


    }
}