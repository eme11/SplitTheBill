package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyApartmentFragment extends Fragment implements View.OnClickListener {

    Button buttonLeave_;
    Button buttonAddUsers_;

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

        buttonAddUsers_.setOnClickListener(this);
        buttonLeave_.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_send_invite_my_apartment:

                break;
            case R.id.button_leave_my_apartment:

                break;
        }
    }
}
