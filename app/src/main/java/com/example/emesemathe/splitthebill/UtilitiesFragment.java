package com.example.emesemathe.splitthebill;


import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class UtilitiesFragment extends Fragment {


    private EditText name_;
    private EditText price_;
    private TextView header_;
    private ArrayList<HouseHoldSupply> houseHoldSupplies_;

    public UtilitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_utilities, container, false);

        name_ = view.findViewById(R.id.add_name_of_product);
        price_ = view.findViewById(R.id.add_price_of_product);
        header_ = view.findViewById(R.id.last_split_header);

        houseHoldSupplies_ = new ArrayList<>();

        Button splitNow = (Button) view.findViewById(R.id.button_split_now);
        splitNow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    splitUtilitiesNow();
                }
            }
        });


        Button addUtilities = view.findViewById(R.id.button_add_household_supplies);
        addUtilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String name = name_.getText().toString();
                    String price = price_.getText().toString().trim();

                    HouseHoldSupply hs = new HouseHoldSupply(uid, name, price);
                    DatabaseReference mRef = FirebaseDatabase.getInstance().
                            getReference("householdSupplies");

                    String id = mRef.push().getKey();

                    mRef.child(id).setValue(hs).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Add Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        Button viewHS = view.findViewById(R.id.button_view_list_of_supplies);
        viewHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void splitUtilitiesNow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           String time =  LocalDateTime.now().toString();
            header_.setText("Last split " + time);
        }
    }

    private void viewList() {
        retrieveHouseholdSupplies();
    }

    private boolean checkFields() {
        String name = name_.getText().toString();
        String price = price_.getText().toString().trim();

        if (name.isEmpty()) {
            name_.setError("Name is required!");
            name_.requestFocus();
            return false;
        }

        if (price.isEmpty()) {
            price_.setError("Price is required!");
            price_.requestFocus();
            return false;
        }

        if (!price.matches("[0-9]+.[0-9]+")) {
            price_.setError("Only numbers!");
            price_.requestFocus();
            return false;
        }
        return true;
    }

    private void retrieveHouseholdSupplies()
    {
        Log.i("My Apartment ", "retireve Users");
        DatabaseReference mRef_ = FirebaseDatabase.getInstance().getReference("householdSupplies");

        mRef_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    // setIdApartment_(aid.getValue(Apartment.class).getIdApartment_());
                    HouseHoldSupply hs = new HouseHoldSupply();
                    hs.setUid_(data.getValue(HouseHoldSupply.class).getUid_());
                    hs.setName_(data.getValue(HouseHoldSupply.class).getName_());
                    hs.setPrice_(data.getValue(HouseHoldSupply.class).getPrice_());
                    Log.i("Utilities", hs.toString());
                    houseHoldSupplies_.add(hs);

                }

                AlertDialog.Builder popUp = new AlertDialog.Builder(getActivity());

                LayoutInflater layout = getLayoutInflater();

                final View view = layout.inflate(R.layout.layout_view_household_supplies, null);
                ExpandableListView list = view.findViewById(R.id.household_supply_list);

                popUp.setView(view);

                popUp.setTitle("View household supplies");

                showExtendedList(list);

                AlertDialog alertDialog = popUp.create();
                alertDialog.show();
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

    private ArrayList<String> initlizeHeader()
    {
        ArrayList<String> hs = new ArrayList<>();
        Log.i("My Aparment", "InitilizeHeader");

        for(HouseHoldSupply tmp: houseHoldSupplies_)
        {
            hs.add(tmp.getName_());
            Log.i("My Aparment", tmp.getName_());
        }
        return hs;
    }

    private HashMap<String, ArrayList<String>> initilizeInfo()
    {
        HashMap<String, ArrayList<String>> information = new HashMap<>();
        Log.i("My Aparment", "InitilizeUserInfo");

        for(HouseHoldSupply tmp : houseHoldSupplies_)
        {
            ArrayList<String> t = new ArrayList<>();

            t.add(String.valueOf(tmp.getPrice_()));
            t.add("USER");

            information.put(tmp.getName_(),t);
        }
        return information;
    }


    private void showExtendedList(ExpandableListView list)
    {
        Log.i("Utilities", "show extended list");

        ExpendableListViewAdapterApartment adapterApartment =
                new ExpendableListViewAdapterApartment(getActivity().getApplicationContext(),
                        initlizeHeader(), initilizeInfo());
        list.setAdapter(adapterApartment);
    }
    //to do fetch users

}