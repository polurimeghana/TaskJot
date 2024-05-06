package com.vvit.project2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>{
    Context context;
    RealmResults<Note1> noteList;

    public Myadapter(Context context, RealmResults<Note1> noteList) {
        this.context=context;
        this.noteList=noteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note1 note1=noteList.get(position);
        holder.titleoutput.setText(note1.getTitle());
        holder.descriptionoutput.setText(note1.getDescription());
        String formatedTime = DateFormat.getDateTimeInstance().format(note1.createdTime);
        holder.timeoutput.setText(formatedTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Notedetails1.class);
                intent.putExtra("title",note1.getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Description",note1.getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu=new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("DELETE")){
                            Realm realm=Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note1.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context.getApplicationContext(), "Note Deleted",Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleoutput;
        TextView descriptionoutput;
        TextView timeoutput;
        public MyViewHolder(View itemView){
            super(itemView);
            titleoutput=itemView.findViewById(R.id.titleoutput);
            descriptionoutput=itemView.findViewById(R.id.descriptionoutput);
            timeoutput=itemView.findViewById(R.id.timeoutput);


        }
    }
}


