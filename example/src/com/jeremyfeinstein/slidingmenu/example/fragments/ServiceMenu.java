package com.jeremyfeinstein.slidingmenu.example.fragments;

import android.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class ServiceMenu extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, R.id.text1, new String[]{"Service 1", "Service 2", "Service 3"}));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getActivity() instanceof Callback) {
            ((Callback) getActivity()).onServiceOptionSelected(position);
        }
    }

    public static interface Callback {
        void onServiceOptionSelected(int position);
    }
}