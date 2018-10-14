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
public class SignUpFragment extends Fragment implements View.OnClickListener{


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        /*Button signUp = (Button) view.findViewById(R.id.button_sing_up_in_fragment_sign_up);
        signUp.setOnClickListener(this);*/

        return view;
    }

    @Override
    public void onClick(View v) {
        
    }
}
