package com.example.emesemathe.splitthebill;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private ListView userSettings_;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        userSettings_ = v.findViewById(R.id.user_settings);
        updateListView();

        return v;
    }

    private void updateListView()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Change Password");
        arrayList.add("Change Email");
        arrayList.add("Change Phone Number");
        arrayList.add("Change Theme");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayList);
        userSettings_.setAdapter(adapter);

        userSettings_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        handlePasswordUpdate();
                        break;
                    case 1:
                        handeleEmailUpdate();
                        break;
                    case 2:
                        handlePhoneNumberChange();
                        break;
                    case 3:
                        handeleThemeChange();
                        break;
                }
            }
        });
    }

    private void handlePasswordUpdate()
    {
        AlertDialog.Builder popUp = new AlertDialog.Builder(getActivity());

        LayoutInflater layout = getLayoutInflater();

        final View view = layout.inflate(R.layout.layout_change_password, null);

        popUp.setView(view);

        final EditText password = view.findViewById(R.id.password_change_password);
        final EditText passwordRe = view.findViewById(R.id.password_change_password_re);
        Button change = view.findViewById(R.id.button_change_password);


        popUp.setTitle("Change password");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields(password, passwordRe))
                    changePassword(password.getText().toString().trim());
            }
        });

        AlertDialog alertDialog = popUp.create();
        alertDialog.show();
    }

    private boolean checkFields(EditText password, EditText passwordRe)
    {
        String pass = password.getText().toString().trim();
        String passRe = passwordRe.getText().toString().trim();

        if(pass.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return false;
        }

        if(passRe.isEmpty()){
            passwordRe.setError("Password is required!");
            passwordRe.requestFocus();
            return false;
        }

        if(pass.length() < 8)
        {
            password.setError("The minimum length for password is 8 characters!");
            password.requestFocus();
            return false;
        }

        if(passRe.length() < 8)
        {
            passwordRe.setError("The minimum length for password is 8 characters!");
            passwordRe.requestFocus();
            return false;
        }

        if(! pass.equals(passRe))
        {
            passwordRe.setError("Passwords don't match!");
            passwordRe.requestFocus();
            password.setError("Passwords don't match!");
            password.requestFocus();
            return false;
        }
        return true;
    }

    private void changePassword(String password)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null)
        {
            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Password Update Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handeleEmailUpdate()
    {
        AlertDialog.Builder popUp = new AlertDialog.Builder(getActivity());

        LayoutInflater layout = getLayoutInflater();

        final View view = layout.inflate(R.layout.latyout_email_change, null);

        popUp.setView(view);

        popUp.setTitle("Change email");

        AlertDialog alertDialog = popUp.create();
        alertDialog.show();
    }

    @SuppressWarnings("unsafe")
    private void handlePhoneNumberChange()
    {
        AlertDialog.Builder popUp = new AlertDialog.Builder(getActivity());

        LayoutInflater layout = getLayoutInflater();

        final View view = layout.inflate(R.layout.latyout_email_change, null);

        popUp.setView(view);

        popUp.setTitle("Change phone number");

        AlertDialog alertDialog = popUp.create();
        alertDialog.show();
    }

    private void handeleThemeChange()
    {
        AlertDialog.Builder popUp = new AlertDialog.Builder(getActivity());

        LayoutInflater layout = getLayoutInflater();

        final View view = layout.inflate(R.layout.latyout_email_change, null);

        popUp.setView(view);

        popUp.setTitle("Change theme");

        AlertDialog alertDialog = popUp.create();
        alertDialog.show();
    }



}
