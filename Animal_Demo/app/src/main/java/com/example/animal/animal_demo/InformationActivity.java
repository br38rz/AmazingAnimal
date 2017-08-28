package com.example.animal.animal_demo;

import com.example.animal.animal_demo.ImageTool.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animal.animal_demo.ImageTool.SetImage;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        GetandSetData();
    }

    //读取设置数据
    public void GetandSetData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            String data=bundle.getString("information");
            String introduction=bundle.getString("introduction");
            String url=bundle.getString("ImageUrl");
            TextView txt_info=(TextView)findViewById(R.id.information);
            TextView txt_intro=(TextView)findViewById(R.id.introduction);
            txt_info.setText(data+"\n");
            txt_intro.setText(introduction);
            ImageView imageView=(ImageView)findViewById(R.id.Animal_Image);
            SetImage.setImageToImageView(imageView,url);
            System.out.print(data);
        }
    }
}
