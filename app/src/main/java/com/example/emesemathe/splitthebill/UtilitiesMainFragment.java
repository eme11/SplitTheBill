package com.example.emesemathe.splitthebill;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UtilitiesMainFragment extends Fragment {

    private ListView utilitiesList_;

    public UtilitiesMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_utilities_main, container, false);

        utilitiesList_ = v.findViewById(R.id.utilities_list);
        handleListUpdate();

        return v;
    }

    private void handleListUpdate()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Gas");
        arrayList.add("Water");
        arrayList.add("Electricity");
        arrayList.add("Internet");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayList);
        utilitiesList_.setAdapter(adapter);

        utilitiesList_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

}
