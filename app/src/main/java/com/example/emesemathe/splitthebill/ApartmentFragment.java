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
public class ApartmentFragment extends Fragment implements View.OnClickListener{


    private Button createApartment;

    public ApartmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_apartment, container, false);

        createApartment = (Button) v.findViewById(R.id.button_create_apartment);
        createApartment.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
