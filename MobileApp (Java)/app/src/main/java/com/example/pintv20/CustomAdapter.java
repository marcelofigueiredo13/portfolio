package com.example.pintv20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListItem> {
    private Context context;
    private Integer resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListItem> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource, parent, false);

        ImageView iv = convertView.findViewById(R.id.like);
        TextView tv = convertView.findViewById(R.id.local);
        TextView date = convertView.findViewById(R.id.date);
        TextView time = convertView.findViewById(R.id.time);

        iv.setImageResource(getItem(position).getImage());
        tv.setText(getItem(position).getComment());
        date.setText(getItem(position).getDate());
        time.setText(getItem(position).getTime());

        return convertView;
    }

    /*
    CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CustomList> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView ==  null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_report_evaluations, parent, false);
        }

        CustomList currentItem = getItem(position);

        ImageView iv = listItemView.findViewById(R.id.like);
        assert currentItem != null;
        iv.setImageResource(currentItem.getImgResId());

        TextView tv = listItemView.findViewById(R.id.comment);
        tv.setText(currentItem.getComment());

        return listItemView;
    }*/
}
