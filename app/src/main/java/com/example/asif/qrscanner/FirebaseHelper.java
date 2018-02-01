package com.example.asif.qrscanner;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by asif on 01-Feb-18.
 */

public class FirebaseHelper {
    private String id;
    DatabaseReference participantRef;
    static ArrayList<String> updated = new ArrayList<String>();

    public void Scan(final String id, final Context context, final boolean activity) {
        this.id = id;

        if (check(id)) {
            Toast.makeText(context,"Already Scanned!!",Toast.LENGTH_SHORT).show();
        } else {

            final ProgressDialog progressDialog = new ProgressDialog(context);

            if(activity) {
                progressDialog.setTitle("Uploading in Database");
                progressDialog.setMessage("Please wait....");
                progressDialog.show();
            }
            participantRef = FirebaseDatabase.getInstance().getReference().child("Participants");

            participantRef.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(id)) {

                        participantRef.child(id).child("scan").setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    updated.add(id);
                                    Toast.makeText(context, "\uD83D\uDC4D\uD83C\uDFFB Scan success! ", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, " Scan again! ", Toast.LENGTH_SHORT).show();
                                }
                                if(activity)
                                progressDialog.dismiss();
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    if(activity)
                    progressDialog.dismiss();
                }
            });
        }
    }

    private boolean check(String id) {
        for(int i =0;i<updated.size();i++){
            if(id.equals(updated.get(i))){
                return true;
            }
        }
        return false;
    }

}
