package com.example.admin.firebase_image;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class UserListAdapter extends ArrayAdapter<Users> {
    private Activity context;
    private int resource;
    private List<Users> listImage;

    public UserListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Users> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tv_Name = (TextView) v.findViewById(R.id.name);
        TextView tv_Email = (TextView) v.findViewById(R.id.email);
        TextView tv_Mobile = (TextView) v.findViewById(R.id.mobile);
        TextView tv_Message = (TextView) v.findViewById(R.id.message);

        ImageView img = (ImageView) v.findViewById(R.id.imageView);

        tv_Name.setText(listImage.get(position).getName());
        tv_Email.setText(listImage.get(position).getEmail());
        tv_Mobile.setText(listImage.get(position).getMobile());
        tv_Message.setText(listImage.get(position).getMessage());

         Glide.with(context).load(listImage.get(position).getUrl()).into(img);

        return v;

    }
}
