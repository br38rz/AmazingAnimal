package com.example.animal.animal_demo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FirstSearchActivity extends AppCompatActivity {
    private TextView title;
    private ViewPager viewPager;
    private ArrayList<View> pageView;
    private ImageButton left,mid,right;
    private String animal_info;
    private String introduction;
    private String ImageUrl;
    private String[] animal_arr=new String[18];
    private String[] animal_intro=new String[18];
    private String[] animal_url=new String[18];

    //从内部类中提取信息
    private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                List<Phylum_33_Chordata>list=(List<Phylum_33_Chordata>)msg.obj;
//                animal_info="名称:"+list.get(0).getAname()+"\n"+"界:"+list.get(0).getAKingdom()+"\n"+"门:"+list.get(0).getAPhyluM()+"\n"
//                        +"纲:"+list.get(0).getAClass()+"\n"+"目:"+list.get(0).getAOrder()+"\n"+"科:"+list.get(0).getAFamily()+"\n"
//                        +"属:"+list.get(0).getAGenus()+"\n"+"种:"+list.get(0).getASpecies()+"\n";
//                introduction="介绍:"+list.get(0).getAIntroduction();
                for(int i=0;i<18;i++){
                    animal_info="名称:"+list.get(i).getAname()+"\n"+"界:"+list.get(i).getAKingdom()+"\n"+"门:"+list.get(i).getAPhyluM()+"\n"
                            +"纲:"+list.get(i).getAClass()+"\n"+"目:"+list.get(i).getAOrder()+"\n"+"科:"+list.get(i).getAFamily()+"\n"
                            +"属:"+list.get(i).getAGenus()+"\n"+"种:"+list.get(i).getASpecies()+"\n";
                    introduction="介绍:"+list.get(i).getAIntroduction();
                    ImageUrl=list.get(i).getAImage().getFileUrl();
                    animal_arr[i]=animal_info;
                    animal_intro[i]=introduction;
                    animal_url[i]=ImageUrl;
                }
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_search);
        Bmob.initialize(this,"e749d7d85bb3e4d25909002d18840393");
        initView();
         setView();
        setInformation();
        new Thread(){
            @Override
            public void run() {
                setButtonClick();
            }
        }.start();
    }

    public void initView(){
        title=(TextView)findViewById(R.id.title);
        AssetManager assetManager=getAssets();
        Typeface typeface=Typeface.createFromAsset(assetManager,"fonts/NewFont.OTF");
        title.setTypeface(typeface);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        left=(ImageButton)findViewById(R.id.btn_left_search);
        mid=(ImageButton)findViewById(R.id.btn_mid_search);
        right=(ImageButton)findViewById(R.id.btn_right_search);
    }

    //从数据库中提取数据
    public void setInformation(){
        BmobQuery<Phylum_33_Chordata>query=new BmobQuery<Phylum_33_Chordata>();
        //query.addWhereEqualTo("Aname",name);
        query.addWhereContains("Ano","00");
        query.findObjects(new FindListener<Phylum_33_Chordata>() {
            @Override
            public void done(List<Phylum_33_Chordata> list, BmobException e) {
                if(e==null){
                    Message message=handler.obtainMessage();
                    message.what=0;
                    message.obj=list;
                    handler.sendMessage(message);
                }else{
                    Toast.makeText(getApplicationContext(),"查询失败",Toast.LENGTH_SHORT).show();
                    System.out.print("错误是："+e.getErrorCode()+" "+e.getMessage());
                }
            }
        });
    }

    //犀牛点击事件
    public void rh_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[9]);
            intent.putExtra("introduction", animal_intro[9]);
            intent.putExtra("ImageUrl",animal_url[9]);
            startActivity(intent);
        }
    }

    //鳄鱼点击事件
    public void cro_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[10]);
            intent.putExtra("introduction", animal_intro[10]);
            intent.putExtra("ImageUrl",animal_url[10]);
            startActivity(intent);
        }
    }

    //乌龟点击事件
    public void tor_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[11]);
            intent.putExtra("introduction", animal_intro[11]);
            intent.putExtra("ImageUrl",animal_url[11]);
            startActivity(intent);
        }
    }

    //狮子点击事件
    public void lion_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[12]);
            intent.putExtra("introduction", animal_intro[12]);
            intent.putExtra("ImageUrl",animal_url[12]);
            startActivity(intent);
        }
    }

    //大象点击事件
    public void ele_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[13]);
            intent.putExtra("introduction", animal_intro[13]);
            intent.putExtra("ImageUrl",animal_url[13]);
            startActivity(intent);
        }
    }

    //猴子点击事件
    public void mon_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[14]);
            intent.putExtra("introduction", animal_intro[14]);
            intent.putExtra("ImageUrl",animal_url[14]);
            startActivity(intent);
        }
    }

    //松鼠点击事件
    public void squ_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[15]);
            intent.putExtra("introduction", animal_intro[15]);
            intent.putExtra("ImageUrl",animal_url[15]);
            startActivity(intent);
        }
    }

    //兔子点击事件
    public void rab_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[16]);
            intent.putExtra("introduction", animal_intro[16]);
            intent.putExtra("ImageUrl",animal_url[16]);
            startActivity(intent);
        }
    }

    //长颈鹿点击事件
    public void gir_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[17]);
            intent.putExtra("introduction", animal_intro[17]);
            intent.putExtra("ImageUrl",animal_url[17]);
            startActivity(intent);
        }
    }

    //骆驼点击事件
    public void cam_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[0]);
            intent.putExtra("introduction", animal_intro[0]);
            intent.putExtra("ImageUrl",animal_url[0]);
            startActivity(intent);
        }
    }

    //斑马点击事件
    public void zrb_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[1]);
            intent.putExtra("introduction", animal_intro[1]);
            intent.putExtra("ImageUrl",animal_url[1]);
            startActivity(intent);
        }
    }

    //熊猫点击事件
    public void pan_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[2]);
            intent.putExtra("introduction", animal_intro[2]);
            intent.putExtra("ImageUrl",animal_url[2]);
            startActivity(intent);
        }
    }

    //鹦鹉点击事件
    public void par_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[3]);
            intent.putExtra("introduction", animal_intro[3]);
            intent.putExtra("ImageUrl",animal_url[3]);
            startActivity(intent);
        }
    }

    //狐狸点击事件
    public void fox_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[4]);
            intent.putExtra("introduction", animal_intro[4]);
            intent.putExtra("ImageUrl",animal_url[4]);
            startActivity(intent);
        }
    }

    //河马点击事件
    public void hip_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[5]);
            intent.putExtra("introduction", animal_intro[5]);
            intent.putExtra("ImageUrl",animal_url[5]);
            startActivity(intent);
        }
    }

    //孔雀点击事件
    public void pea_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[6]);
            intent.putExtra("introduction", animal_intro[6]);
            intent.putExtra("ImageUrl",animal_url[6]);
            startActivity(intent);
        }
    }

    //驴点击事件
    public void ass_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[7]);
            intent.putExtra("introduction", animal_intro[7]);
            intent.putExtra("ImageUrl",animal_url[7]);
            startActivity(intent);
        }
    }

    //羊点击事件
    public void she_Click(View v){
        if(animal_info!=null&&introduction!=null) {
            Intent intent = new Intent(FirstSearchActivity.this, InformationActivity.class);
            intent.putExtra("information", animal_arr[8]);
            intent.putExtra("introduction", animal_intro[8]);
            intent.putExtra("ImageUrl",animal_url[8]);
            startActivity(intent);
        }
    }

    //设置ViewPager
    public void setView(){
        LayoutInflater layoutInflater=getLayoutInflater();
        View view1=layoutInflater.inflate(R.layout.search_one,null);
        View view2=layoutInflater.inflate(R.layout.search_two,null);
        pageView=new ArrayList<View>();
        //添加第一个界面
        pageView.add(view1);
        //添加第二个界面
        pageView.add(view2);
        new Thread(){
            @Override
            public void run() {
                PagerAdapter myPageAdapter=new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return pageView.size();
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view==object;
                    }

                    @Override
                    public void destroyItem(View container, int position, Object object) {
                        ((ViewPager)container).removeView(pageView.get(position));
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {
                        container.addView(pageView.get(position));
                        return pageView.get(position);
                    }
                };
                viewPager.setAdapter(myPageAdapter);
                viewPager.setCurrentItem(0);
            }
        }.start();
    }

    //设置按钮点击事件
    public void setButtonClick(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //refresh();
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstSearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstSearchActivity.this,VersionActivity.class);
                startActivity(intent);
            }
        });
    }

    //刷新界面
    public void refresh(){
        finish();
        Intent intent=new Intent(FirstSearchActivity.this,FirstSearchActivity.class);
        startActivity(intent);
        finish();
    }
}
