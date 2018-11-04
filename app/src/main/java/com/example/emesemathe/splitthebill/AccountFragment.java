package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private TextView textView;
    private FirebaseAuth math;
    private FirebaseUser user_;

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

        textView = (TextView) v.findViewById(R.id.current_email_account);
        String str = "Your current email is " + user_.getEmail();

        textView.setText(str);

        return v;
    }

}
