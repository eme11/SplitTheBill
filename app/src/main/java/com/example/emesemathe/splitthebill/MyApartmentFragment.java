package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyApartmentFragment extends Fragment{

    Button buttonLeave_;
    Button buttonAddUsers_;

    private ExpandableListView peopleList_;
    private Apartment apartment;
    private ArrayList<User> usersList;

    public MyApartmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_apartment, container, false);

        buttonAddUsers_ = (Button) v.findViewById(R.id.button_send_invite_my_apartment);
        buttonLeave_ = (Button) v.findViewById(R.id.button_leave_my_apartment);
        peopleList_ = v.findViewById(R.id.people_list);

        /*Bundle bundle = getArguments();
        apartment= (Apartment) bundle.getSerializable("apartment");*/
        usersList = new ArrayList<>();

        updateList();


        buttonAddUsers_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsers();
            }
        });
        buttonLeave_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUser();
            }
        });
        return v;
    }

    private void removeUser() {
    }

    private void addUsers() {
    }

    private void updateList()
    {
        //retriveUsers();
        usersList.add(new User());
        usersList.add(new User("id", "email" , "07400000", "name"));
        ExpendableListViewAdapterApartment adapterApartment =
                new ExpendableListViewAdapterApartment(getActivity().getApplicationContext(),
                        initilizaHeader(), initilizeUserInforamtion());
        peopleList_.setAdapter(adapterApartment);
    }

    private ArrayList<String> initilizaHeader()
    {
        ArrayList<String> users = new ArrayList<>();

        for(User tmp: usersList)
        {
            users.add(tmp.getUserName_());
        }
        return users;
    }

    private HashMap<String, ArrayList<String>> initilizeUserInforamtion()
    {
        HashMap<String, ArrayList<String>> information = new HashMap<String, ArrayList<String>>();

        for(User tmp : usersList)
        {
            ArrayList<String> t = new ArrayList<>();

            t.add(tmp.getEmailAddress_());
            t.add(tmp.getPhoneNumber_());

            information.put(tmp.getUserName_(),t);
        }
        return information;
    }



    /*private ArrayList<User> retriveUsers()
    {
        DatabaseReference mRef_ = FirebaseDatabase.getInstance().getReference("users");
        ArrayList<String> ids = apartment.getUserId_();

        for(String i : ids )
        {
            final String tmp = i;
            mRef_.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    getUsersFromDatabase(dataSnapshot, tmp);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity().getApplicationContext(), databaseError.getCode(),
                            Toast.LENGTH_SHORT).show();

                }
            });
        }
        return  null;
    }

    private void getUsersFromDatabase(DataSnapshot dataSnapshot, String userId) {

        User user = dataSnapshot.child(userId).getValue(User.class);

        usersList.add(user);

        Log.i("Data Base get user:" , user.toString());
    }*/
}
