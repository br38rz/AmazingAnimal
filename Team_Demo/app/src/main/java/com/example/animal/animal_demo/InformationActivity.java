package com.example.animal.animal_demo;

import com.example.animal.animal_demo.ImageTool.*;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.animal.animal_demo.ImageTool.SetImage;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import pl.droidsonroids.gif.GifImageView;

public class InformationActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RadioButton map,search,us_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        GetandSetData();
        initView();
        setButtonClick();
        Fresco.initialize(this);
    }

    //初始化
    public void initView(){
        mRadioGroup=(RadioGroup)findViewById(R.id.mRadioGroup);
        search=(RadioButton)findViewById(R.id.rbtn_search);
        map=(RadioButton)findViewById(R.id.btn_map);
        us_info=(RadioButton)findViewById(R.id.btn_version);
    }

    public void setButtonClick(){
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_search:
                        Intent intentsearch=new Intent(InformationActivity.this,FirstSearchActivity.class);
                        startActivity(intentsearch);
                        break;
                    case R.id.btn_map:
                        Intent intent=new Intent(InformationActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_version:
                        Intent intentversion=new Intent(InformationActivity.this,VersionActivity.class);
                        startActivity(intentversion);
                        break;
                }
            }
        });
    }

    //读取设置数据
    public void GetandSetData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            String data=bundle.getString("information");
            String introduction=bundle.getString("introduction");
            String url=bundle.getString("ImageUrl");
            String GIFUrl=bundle.getString("GIFUrl");
            TextView txt_info=(TextView)findViewById(R.id.information);
            TextView txt_intro=(TextView)findViewById(R.id.introduction);
            txt_info.setText(data+"\n");
            txt_intro.setText(introduction);
            ImageView imageView=(ImageView)findViewById(R.id.Animal_Image);
            SetImage.setImageToImageView(imageView,url);
            SimpleDraweeView draweeView=(SimpleDraweeView)findViewById(R.id.Animal_GIF);
            SetImage.setGIF(draweeView,GIFUrl);
        }
    }
}
