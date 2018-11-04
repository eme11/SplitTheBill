package com.example.emesemathe.splitthebill;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentFragment extends Fragment{


    private Button createApartment;

    private EditText name_, address_, rent_;

    public ApartmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_apartment, container, false);

        name_ = (EditText) v.findViewById(R.id.add_apartment_name);
        address_ = (EditText) v.findViewById(R.id.add_apartment_address);
        rent_ = (EditText) v.findViewById(R.id.add_apartment_rent);

        createApartment = (Button) v.findViewById(R.id.button_create_apartment);
        createApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createApartmentForDataBase();
            }
        });

        return v;
    }

    private void createApartmentForDataBase()
    {
        checkFields();
    }

    private void checkFields()
    {
        String name = name_.getText().toString().trim();
        String address = address_.getText().toString().trim();
        String rent = rent_.getText().toString().trim();

        if(name.isEmpty())
        {
            name_.setError("Name is required!");
            name_.requestFocus();
            return;
        }

        if(address.isEmpty())
        {
            address_.setError("Address is required!");
            address_.requestFocus();
            return;
        }

        if(rent.isEmpty())
        {
            rent_.setError("Rent is required!");
            rent_.requestFocus();
            return;
        }

        if(!rent.matches("[0-9]+.[0-9]+"))
        {
            rent_.setError("Only numbers!");
            rent_.requestFocus();
            return;
        }
    }
}
