package com.example.animal.animal_demo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class VersionActivity extends AppCompatActivity {
    private TextView title,info,support,support_info,version,version_info;
    private ViewPager myPage;
    private String info1,info2;
    private ArrayList<View> myView;
    private ImageButton left,mid,right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        initView();
        setViewPager();
        setButtonClick();
    }

    //初始化
    public void initView(){
        myPage=(ViewPager)findViewById(R.id.viewPager_version);
        left=(ImageButton)findViewById(R.id.btn_left_version);
        mid=(ImageButton)findViewById(R.id.btn_mid_version);
        right=(ImageButton)findViewById(R.id.btn_right_version);
    }

    /**
     设置aboutus.xml,supportus.xml,version.xml中的相关内容
     * 设置字体格式
     * 设置信息文字内容
     **/
    public void setInfo(){
        AssetManager assetManager=getAssets();
        LayoutInflater myInflater=getLayoutInflater();
        //aboutus.xml
        View view1=myInflater.inflate(R.layout.aboutus,(ViewGroup)findViewById(R.id.aboutus));
        title=(TextView)view1.findViewById(R.id.title_about);
        Typeface typeface=Typeface.createFromAsset(assetManager,"fonts/VersionFont.TTF");
        title.setTypeface(typeface);
        info=(TextView)view1.findViewById(R.id.aboutus_info);
        info1="我们的APP以动物园为基础，通过让用户扫描二维码的方式，对动物园中的游客们介绍各类动物的生活习性，居住范围等百科知识，展示动物的生活状态等";
        info2="我们的目的是向更多的人传播有关动物的知识，使得更多的人们来关爱动物，保护动物";
        info.setText("\u3000\u3000"+info1+"\n"+"\u3000\u3000"+info2);
        //supportus.xml
        View view2=myInflater.inflate(R.layout.supportus,(ViewGroup)findViewById(R.id.supportus));
        support=(TextView)view2.findViewById(R.id.title_support);
        support.setTypeface(typeface);
        support_info=(TextView)view2.findViewById(R.id.support_info);
        String info3="为了更好的改善和提升本产品";
        String info4="请您大胆的提出想法和建议";
        String info5="快去应用商店为我们评分吧 (´・ω・`)";
        support_info.setText("\u3000\u3000"+info3+"\n"+"\u3000\u3000"+info4+"\n"+"\u3000\u3000"+info5);
        //version.xml
        View view3=myInflater.inflate(R.layout.version,(ViewGroup)findViewById(R.id.version));
        version=(TextView)view3.findViewById(R.id.title_version);
        version.setTypeface(typeface);
        version_info=(TextView)view3.findViewById(R.id.version_info);
        String info6="最新版本号:1.0.0";
        String info7="更新时间:2017.7.3";
        version_info.setText("\u3000\u3000"+info6+"\n"+"\u3000\u3000"+info7);
    }

    //设置ViewPager
    public void setViewPager(){
        LayoutInflater myInflater=getLayoutInflater();
        View firstView=myInflater.inflate(R.layout.aboutus,null);
        View secondView=myInflater.inflate(R.layout.supportus,null);
        View thirdView=myInflater.inflate(R.layout.version,null);
        myView=new ArrayList<View>();
        myView.add(firstView);
        myView.add(secondView);
        myView.add(thirdView);
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return myView.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(myView.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(myView.get(position));
                return myView.get(position);
            }
        };
        myPage.setAdapter(pagerAdapter);
        myPage.setCurrentItem(0);
    }

    //设置按钮点击事件
    public void setButtonClick(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VersionActivity.this,FirstSearchActivity.class);
                startActivity(intent);
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VersionActivity.this,FirstSearchActivity.class);
                startActivity(intent);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
