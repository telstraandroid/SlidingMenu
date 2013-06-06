package com.jeremyfeinstein.slidingmenu.example.fragments;

import android.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NavigationMenu extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, R.id.text1, new String[]{"Navigation 1", "Navigation 2", "Navigation 3"}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(R.id.text1);
                Drawable drawable = getResources().getDrawable(com.jeremyfeinstein.slidingmenu.example.R.drawable.ic_launcher);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                textView.setCompoundDrawables(drawable, null, null, null);
                textView.setCompoundDrawablePadding(16);
                return view;
            }
        });
        getListView().setOnItemClickListener(this);
        getListView().setBackgroundColor(getResources().getColor(R.color.darker_gray));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getActivity() instanceof Callback) {
            ((Callback) getActivity()).onNavigationOptionSelected(position);
        }
    }

    public static interface Callback {
        void onNavigationOptionSelected(int position);
    }
}