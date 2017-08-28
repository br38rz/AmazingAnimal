package com.example.animal.animal_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.example.animal.animal_demo.fragment.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton left,mid,right;
    private MapFragment MapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment = new MapFragment();
        initView();
        setButtonClick();
        switchFragment(MapFragment);
    }

    public void initView(){
        left=(ImageButton)findViewById(R.id.btn_left_main);
        mid=(ImageButton)findViewById(R.id.btn_mid_main);
        right=(ImageButton)findViewById(R.id.btn_right_main);
    }

    public void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    public void setButtonClick(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FirstSearchActivity.class);
                startActivity(intent);
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //refresh();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VersionActivity.class);
                startActivity(intent);
            }
        });
    }

    public void refresh(){
        finish();
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
