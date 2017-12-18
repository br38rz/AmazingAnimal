package com.example.animal.animal_demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.animal.animal_demo.fragment.*;

public class MainActivity extends AppCompatActivity {
//    private ImageButton left,mid,right;
    private RadioGroup mRadioGroup;
    private RadioButton map,search,us_info;
    private MapFragment MapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment = new MapFragment();
        initView();
        setButtonClick();
//        setButtonClick();
        switchFragment(MapFragment);
    }

    public void initView(){
//        left=(ImageButton)findViewById(R.id.btn_left_main);
//        mid=(ImageButton)findViewById(R.id.btn_mid_main);
//        right=(ImageButton)findViewById(R.id.btn_right_main);
        mRadioGroup=(RadioGroup)findViewById(R.id.mRadioGroup);
        search=(RadioButton)findViewById(R.id.btn_search);
        map=(RadioButton)findViewById(R.id.btn_map);
        us_info=(RadioButton)findViewById(R.id.btn_version);
    }

    public void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    public void setButtonClick(){
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_search:
                        Intent intent=new Intent(MainActivity.this,FirstSearchActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_map:
                        refresh();
                        break;
                    case R.id.btn_version:
                        Intent intentversion=new Intent(MainActivity.this,VersionActivity.class);
                        startActivity(intentversion);
                        break;
                }
//               setTabState();
            }
        });
    }

//    public void setTabState(){
//        setSearchState();
//        setMapState();
//        setVersionState();
//    }
//
//    private void setVersionState() {
//        if(us_info.isChecked()){
//            us_info.setTextColor(Color.BLUE);
//        }else{
//            us_info.setTextColor(Color.GREEN);
//        }
//    }
//
//    private void setMapState() {
//        if(map.isChecked()){
//            map.setTextColor(Color.BLUE);
//        }else{
//            map.setTextColor(Color.GREEN);
//        }
//    }
//
//    private void setSearchState() {
//        if(search.isChecked()){
//            search.setTextColor(Color.BLUE);
//        }else{
//            search.setTextColor(Color.GREEN);
//        }
//    }
//    public void setButtonClick(){
//        left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,FirstSearchActivity.class);
//                startActivity(intent);
//            }
//        });
//        mid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //refresh();
//            }
//        });
//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,VersionActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    public void refresh(){
        finish();
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
