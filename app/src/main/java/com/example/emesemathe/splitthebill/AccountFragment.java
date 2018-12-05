package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private FirebaseAuth math;
    private FirebaseUser user_;

    private ListView userInformation_;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        math = FirebaseAuth.getInstance();
        user_ = math.getCurrentUser();

        userInformation_ = v.findViewById(R.id.user_information);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Something random");
        arrayList.add("Something random");
        arrayList.add("Something random");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        userInformation_.setAdapter(adapter);

        return v;
    }

}
