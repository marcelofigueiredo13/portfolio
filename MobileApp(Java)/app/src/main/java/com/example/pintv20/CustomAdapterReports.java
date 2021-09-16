package com.example.pintv20;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomAdapterReports extends ArrayAdapter<ListItemReports> {
    private Context context;
    private Integer resource;


    public CustomAdapterReports(@NonNull Context context, int resource, @NonNull List<ListItemReports> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource, parent, false);

        TextView tvBoard = convertView.findViewById(R.id.board);
        TextView tvLocal = convertView.findViewById(R.id.local);
        TextView tvDate = convertView.findViewById(R.id.date);
        TextView tvTime = convertView.findViewById(R.id.time);

        if(getItem(position).getLevel().equals(1)){
            tvBoard.setBackground(ContextCompat.getDrawable(context, R.drawable.report_dialog_green));
        }
        if(getItem(position).getLevel().equals(2)){
            tvBoard.setBackground(ContextCompat.getDrawable(context, R.drawable.report_dialog_orange));
        }
        if(getItem(position).getLevel().equals(3)){
            tvBoard.setBackground(ContextCompat.getDrawable(context, R.drawable.report_dialog_red));
        }

        tvLocal.setText(getItem(position).getLocal());
        tvDate.setText(getItem(position).getDate());
        tvTime.setText(getItem(position).getTime());
        return convertView;
    }
}
