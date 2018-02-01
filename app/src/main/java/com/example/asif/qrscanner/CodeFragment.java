package com.example.asif.qrscanner;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;


/**
 * A simple {@link Fragment} subclass.
 */
public class CodeFragment extends Fragment {

    TextInputEditText mCode;
    Button Submit;
    private String id;

    FirebaseHelper helper;

    public CodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_code, container, false);
        mCode=(TextInputEditText)v.findViewById(R.id.codeText);
        Submit=(Button)v.findViewById(R.id.submit);

        helper = new FirebaseHelper();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = mCode.getText().toString();
                if(!id.equals("PVP-")){
                    //Toast.makeText(getContext(),"result "+id,Toast.LENGTH_SHORT).show();
                                    helper.Scan(id,getContext(),true);
                }
            }
        });
        return v;
    }

}
