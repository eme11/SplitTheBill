package com.example.emesemathe.splitthebill;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpendableListViewAdapterApartment extends BaseExpandableListAdapter {

    private ArrayList<String> userName_;
    private HashMap<String, ArrayList<String>> userInfo_;
    private Context context_;

    public ExpendableListViewAdapterApartment(Context context, ArrayList<String> users,
                                              HashMap<String, ArrayList<String>> info)
    {
        userName_ = users;
        userInfo_ = info;
        context_ = context;
    }

    @Override
    public int getGroupCount() {
        return userName_ != null ? userName_.size() : 0 ;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return userInfo_.get(userName_.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return userName_.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.i("GET CHILD", groupPosition + " ");
        return userInfo_.get(userName_.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String name = userName_.get(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context_
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView text = convertView.findViewById(R.id.list_header_expandable);
        text.setText(name);
        text.setTextColor(Color.DKGRAY);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String info = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context_
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView text = convertView.findViewById(R.id.list_item_expandable);
        text.setText(info);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
