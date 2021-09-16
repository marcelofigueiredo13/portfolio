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

public class CustomAdapterRanking extends ArrayAdapter<ListItemRanking> {
    private Context context;
    private Integer resource;
    private Database db = new Database();

    public CustomAdapterRanking(@NonNull Context context, int resource, @NonNull List<ListItemRanking> items) {
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
        TextView tvNome = convertView.findViewById(R.id.name);
        TextView tvPts = convertView.findViewById(R.id.points);
        ImageView ivAvatar = convertView.findViewById(R.id.avatar);

        if(getItem(position).getNome().equals(db.getCurrentUserName())){
            tvBoard.setBackground(ContextCompat.getDrawable(context, R.drawable.background_current_user));
            tvNome.setTextColor(Color.parseColor("#FFFFFF"));
            tvPts.setTextColor(Color.parseColor("#FFFFFF"));
        }

        if(getItem(position).getPontos() >= 50)
        {
            ivAvatar.setImageResource(R.drawable.avatar_3);
        }
        else{
            if(getItem(position).getPontos() >= 25)
            {
                ivAvatar.setImageResource(R.drawable.avatar_2);
            }
            else
                ivAvatar.setImageResource(R.drawable.avatar_1);
        }
//        if(getItem(position).getAvatar().equals(1)){
//            ivAvatar.setImageResource(R.drawable.avatar_1);
//        }
//        if(getItem(position).getAvatar().equals(2)){
//            ivAvatar.setImageResource(R.drawable.avatar_2);
//        }
//        if(getItem(position).getAvatar().equals(3)){
//            ivAvatar.setImageResource(R.drawable.avatar_3);
//        }

        tvNome.setText(getItem(position).getNome());
        tvPts.setText(String.valueOf(getItem(position).getPontos()) + " Pts");

        return convertView;
    }
}
