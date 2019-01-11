package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
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
    private String aparmentId;
    private ArrayList<User> usersList;
    private ArrayList<String> ids;

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

        usersList = new ArrayList<>();
        apartment = new Apartment();

        retriveApartment();


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

    private void showExtendedList()
    {
        Log.i("My Apartment", "show extended list");
        ExpendableListViewAdapterApartment adapterApartment =
                new ExpendableListViewAdapterApartment(getActivity().getApplicationContext(),
                        initilizaHeader(), initilizeUserInforamtion());
        peopleList_.setAdapter(adapterApartment);
    }

    private ArrayList<String> initilizaHeader()
    {
        ArrayList<String> users = new ArrayList<>();
        Log.i("My Aparment", "InitilizeHeader");

        for(User tmp: usersList)
        {
            users.add(tmp.getUserName_());
            Log.i("My Aparment", tmp.getUserName_());
        }
        return users;
    }

    private HashMap<String, ArrayList<String>> initilizeUserInforamtion()
    {
        HashMap<String, ArrayList<String>> information = new HashMap<>();
        Log.i("My Aparment", "InitilizeUserInfo");

        for(User tmp : usersList)
        {
            ArrayList<String> t = new ArrayList<>();

            t.add(tmp.getEmailAddress_());
            t.add(tmp.getPhoneNumber_());

            Log.i("My Aparment", tmp.getEmailAddress_());
            Log.i("My Aparment", tmp.getPhoneNumber_());

            information.put(tmp.getUserName_(),t);
        }
        return information;
    }



    private void retriveUsers()
    {
        Log.i("My Apartment ", "retireve Users");
        DatabaseReference mRef_ = FirebaseDatabase.getInstance().getReference("users");
        ids = apartment.getUserId_();

        mRef_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(String tmp: ids)
                    {
                        getUsersFromDatabase(dataSnapshot, tmp);
                        Log.i("My Apartment", "AFTER userFromDatabase");
                        Log.i("My Apartment", tmp);
                    }
                showExtendedList();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Log.i("MyApartment", "OnCancell::retriveUser()");
            }
        });
        Log.i("My Apartment", "AFTER userFromDatabase::onDataChange Ended");
        return;
    }

    private void getUsersFromDatabase(DataSnapshot dataSnapshot, String userId) {

        Log.i("My Apartment ", "get Users from data base");
        User user = dataSnapshot.child(userId).getValue(User.class);
        Log.i("My Apartment", user.toString());
        usersList.add(user);
        return;
    }

    private void retriveApartment()
    {
        Log.i("My Apartment ", "retireve Apartment");
        DatabaseReference mRef_ = FirebaseDatabase.getInstance().getReference("apartmentList");
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.i("My Apartment ", "uid: " + uid);

        mRef_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(getAUI(dataSnapshot, uid)){
                    retriveUsers();
                    Log.i("My Apartment", "AFTER retrieve users");
                }

                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), databaseError.getCode(),
                        Toast.LENGTH_SHORT).show();

            }
        });
        return;
    }

    private boolean getAUI(DataSnapshot dataSnapshot, String uid)
    {
        Log.i("My Apartment ", "retireve AUID");
        boolean found = false;
        String key = null;
        for (DataSnapshot aid: dataSnapshot.getChildren())
        {
            key = aid.getKey();
            Log.i("My Apartment ", "key: " + key);
            DataSnapshot s =aid.child("userId_");
            found = hasChild(s, uid);
            if (found)
            {
                apartment.setIdApartment_(aid.getValue(Apartment.class).getIdApartment_());
                apartment.setName_(aid.getValue(Apartment.class).getName_());
                apartment.setAddress_(aid.getValue(Apartment.class).getAddress_());
                apartment.setRent_(aid.getValue(Apartment.class).getRent_());
                break;
            }
        }

        return found;
    }

    private boolean hasChild(DataSnapshot dataSnapshot,String uid)
    {
        Log.i("My Apartment", "Has child");
        boolean ret = false;
        for(DataSnapshot snapshot: dataSnapshot.getChildren())
        {
            ret = snapshot.getValue().equals(uid);
            apartment.addId((String) snapshot.getValue());
        }
        return ret;
    }


}
