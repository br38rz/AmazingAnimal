package com.example.animal.animal_demo.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animal.animal_demo.CustomView.ZooImageView;
import com.example.animal.animal_demo.R;


public class MapFragment extends Fragment {

    private ZooImageView zoom_image_view_map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = View.inflate(getActivity(), R.layout.fragment_map,null);
        initView(view);
        return view;
    }

    public void initView(View view){
        zoom_image_view_map= (ZooImageView) view.findViewById(R.id.zoom_image_view_map);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        zoom_image_view_map.setImageBitmap(bitmap);
    }
}