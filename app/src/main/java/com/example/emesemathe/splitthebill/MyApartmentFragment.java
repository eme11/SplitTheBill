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

    Button btn_leave;
    Button btn_add;

    public MyApartmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_apartment, container, false);

        btn_add = (Button) v.findViewById(R.id.button_send_invite_my_apartment);
        btn_leave = (Button) v.findViewById(R.id.button_leave_my_apartment);

        btn_add.setOnClickListener(this);
        btn_leave.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_send_invite_my_apartment:

                break;
            case R.id.button_leave_my_apartment:

                break;
        }
    }
}
