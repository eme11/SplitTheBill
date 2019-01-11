package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private ListView userInformation_;
    private String userId_;
    private User currentUser_;

    private FirebaseAuth mAuth_;
    private DatabaseReference mRef_;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        mAuth_ = FirebaseAuth.getInstance();

        userInformation_ = v.findViewById(R.id.user_information);

        dataBaseHandling();

        return v;
    }

    @SuppressWarnings("unchecked")
    void dataBaseHandling()
    {
        mAuth_ = FirebaseAuth.getInstance();
        userId_ = mAuth_.getCurrentUser().getUid();
        mRef_ = FirebaseDatabase.getInstance().getReference("users");
        currentUser_ = new User();

       mRef_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userId_))
                {
                    updateingUserInformation(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), databaseError.getCode(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    @SuppressWarnings("unchecked")
    private void updateingUserInformation(DataSnapshot dataSnapshot)
    {
        currentUser_ = dataSnapshot.child(userId_).getValue(User.class);
        Log.i("Data Base get user:" , currentUser_.toString());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(currentUser_.getEmailAddress_());
        arrayList.add(currentUser_.getUserName_());
        arrayList.add(currentUser_.getPhoneNumber_());

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayList);
        userInformation_.setAdapter(adapter);
    }

}
