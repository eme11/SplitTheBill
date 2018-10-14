package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment implements View.OnClickListener {


    private SignUpFragment signUp_;
    private Button btn_singIn, btn_singUp;
    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        btn_singIn = (Button) view.findViewById(R.id.button_sign_in);
        btn_singUp = (Button) view.findViewById(R.id.button_sign_up);

        btn_singIn.setOnClickListener(this);
        btn_singUp.setOnClickListener(this);

        signUp_ = new SignUpFragment();

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager ;
        switch (v.getId())
        {
            case R.id.button_sign_in:

                break;
            case R.id.button_sign_up:
                manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.sign_in, signUp_).commit();
                break;
        }
    }
}
