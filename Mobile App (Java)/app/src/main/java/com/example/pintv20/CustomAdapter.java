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
}
