package com.example.asif.qrscanner;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipantsFragment extends Fragment {

    private RecyclerView mUsersList;

    private DatabaseReference mParticipantDatabase;
    private LinearLayoutManager mLayoutManager;

    Context ctx;

    public ParticipantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_participants, container, false);

        ctx = container.getContext();
        mParticipantDatabase = FirebaseDatabase.getInstance().getReference().child("Participants");

        mLayoutManager = new LinearLayoutManager(getContext());

        mUsersList = (RecyclerView) v.findViewById(R.id.participant_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(mLayoutManager);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Participants,ParticipantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Participants, ParticipantViewHolder>(
                Participants.class,
                R.layout.participant_single_layout,
                ParticipantViewHolder.class,
                mParticipantDatabase
        ) {
            @Override
            protected void populateViewHolder(final ParticipantViewHolder viewHolder, Participants model, int i) {
                final String list_user_id = getRef(i).getKey();

                ParticipantViewHolder.setDisplayName(model.getName());
                ParticipantViewHolder.setDisplayCollege(model.getCollege());
                ParticipantViewHolder.setDisplayScan(model.isScan());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getContext(),"selected "+list_user_id,Toast.LENGTH_SHORT).show();

                        CharSequence options[] = new CharSequence[]{"Delete Participant","Cancel"};

                        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                        builder.setTitle("Select Options");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(i==0) {
                                    mParticipantDatabase.child(list_user_id).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                if(i==1){
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }
        };

        mUsersList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {

        static View mView;

        public ParticipantViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public static void setDisplayName(String name){
            TextView NameView = (TextView) mView.findViewById(R.id.name_participant);
            NameView.setText(name);
        }

        public static void setDisplayCollege(String name){
            TextView CollegeView = (TextView) mView.findViewById(R.id.college__participant);
            CollegeView.setText(name);
        }

        public static void setDisplayScan(boolean name){
            TextView ScanView = (TextView) mView.findViewById(R.id.scan_participant);
            if(name==true) {
                ScanView.setText("true");
                //ScanView.setTextColor();
            }
        }

    }
}
