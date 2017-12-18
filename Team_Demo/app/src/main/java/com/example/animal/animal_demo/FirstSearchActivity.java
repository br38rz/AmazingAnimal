package com.example.animal.animal_demo;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animal.animal_demo.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FirstSearchActivity extends AppCompatActivity{
    private TextView title;
    private ViewPager viewPager;
    private ArrayList<View> pageView;
//    private ImageButton left,mid,right;
    private ImageButton btn_search;
    private RadioGroup mRadioGroup;
    private RadioButton map,search,us_info;
    private String animal_info;
    private String introduction;
    private String ImageUrl;
    private String GIFUrl;
    private String[] animal_arr=new String[18];
    private String[] animal_intro=new String[18];
    private String[] animal_url=new String[18];
    private String[] animal_gif=new String[18];
    private String[] list={"骆驼","斑马","熊猫","鹦鹉","狐狸","河马","孔雀","驴","羊","犀牛","鳄鱼","乌龟","狮子","大象","猴子","松鼠","兔子","长颈鹿"};
//    private SearchView mySearchView;
//    private ListView myListView;
    private AutoCompleteTextView autoText;
    private SearchFragment searchFragment;

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
                    GIFUrl=list.get(i).getAGIF().getFileUrl();
                    animal_arr[i]=animal_info;
                    animal_intro[i]=introduction;
                    animal_url[i]=ImageUrl;
                    animal_gif[i]=GIFUrl;
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
       new Thread(){
           @Override
           public void run() {
               setInformation();
           }
       }.start();
        setAutoText();
        //switchFragment(searchFragment);
        new Thread(){
            @Override
            public void run() {
                setButtonClick();
                setSearchButton();
            }
        }.start();
    }

    public void initView(){
        title=(TextView)findViewById(R.id.title);
        autoText=(AutoCompleteTextView)findViewById(R.id.btn_search_text);
        btn_search=(ImageButton)findViewById(R.id.btn_search);
        AssetManager assetManager=getAssets();
        Typeface typeface=Typeface.createFromAsset(assetManager,"fonts/NewFont.OTF");
        title.setTypeface(typeface);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
//        left=(ImageButton)findViewById(R.id.btn_left_search);
//        mid=(ImageButton)findViewById(R.id.btn_mid_search);
//        right=(ImageButton)findViewById(R.id.btn_right_search);
        mRadioGroup=(RadioGroup)findViewById(R.id.mRadioGroup);
        search=(RadioButton)findViewById(R.id.rbtn_search);
        map=(RadioButton)findViewById(R.id.btn_map);
        us_info=(RadioButton)findViewById(R.id.btn_version);
//        mySearchView=(SearchView)findViewById(R.id.search_text);
//        mySearchView.setSubmitButtonEnabled(true);
//        myListView=(ListView)findViewById(R.id.search_list);
//        myListView.setAdapter(new ArrayAdapter<String>(this,R.layout.search_list,myStrings));
//        myListView.setTextFilterEnabled(true);
        //searchFragment=new SearchFragment();
//        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getApplicationContext(),"您输入的是:"+query,Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(TextUtils.isEmpty(newText)){
//                    myListView.clearTextFilter();
//                }else{
//                    myListView.setFilterText(newText);
//                }
//                return true;
//            }
//        });
}

    //设置AutoCompleteTextView
    public void setAutoText(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.search_list,list);
        autoText.setAdapter(arrayAdapter);
        autoText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String information=autoText.getText().toString();
                setRespond(information);
                return true;
            }
        });
    }

    //设置搜索按钮
    public void setSearchButton(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information=autoText.getText().toString();
                System.out.println(information);
                if(information==""){
                    Toast.makeText(getApplicationContext(),"请输入搜索内容",Toast.LENGTH_SHORT).show();
                }else{
                    setRespond(autoText.getText().toString());
                }
            }
        });
    }

    //加载Fragment
//    public void switchFragment(android.support.v4.app.Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.search_fragment,fragment).commit();
//    }

//    //用户在搜索框中输入字符时触发的方法
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//    }
//
//    //用户单击搜索按钮时触发的方法
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//
//    }

    //从数据库中提取数据
    public void setInformation(){
        BmobQuery<Phylum_33_Chordata>query=new BmobQuery<Phylum_33_Chordata>();
        //query.addWhereEqualTo("Aname","犀牛");
        query.addQueryKeys("Ano,Aname,AImage,AGIF,AKingdom,APhyluM,AClass,AOrder,AFamily,AGenus,ASpecies,AIntroduction");
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
            intent.putExtra("GIFUrl",animal_gif[9]);
            System.out.println(animal_gif[9]);
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
            intent.putExtra("GIFUrl",animal_gif[10]);
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
            intent.putExtra("GIFUrl",animal_gif[11]);
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
            intent.putExtra("GIFUrl",animal_gif[12]);
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
            intent.putExtra("GIFUrl",animal_gif[13]);
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
            intent.putExtra("GIFUrl",animal_gif[14]);
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
            intent.putExtra("GIFUrl",animal_gif[15]);
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
            intent.putExtra("GIFUrl",animal_gif[16]);
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
            intent.putExtra("GIFUrl",animal_gif[17]);
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
            intent.putExtra("GIFUrl",animal_gif[0]);
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
            intent.putExtra("GIFUrl",animal_gif[1]);
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
            intent.putExtra("GIFUrl",animal_gif[2]);
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
            intent.putExtra("GIFUrl",animal_gif[3]);
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
            intent.putExtra("GIFUrl",animal_gif[4]);
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
            intent.putExtra("GIFUrl",animal_gif[5]);
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
            intent.putExtra("GIFUrl",animal_gif[6]);
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
            intent.putExtra("GIFUrl",animal_gif[7]);
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
            intent.putExtra("GIFUrl",animal_gif[8]);
            startActivity(intent);
        }
    }

    //设置搜索框响应内容
    public void setRespond(String name){
        switch (name){
            case "犀牛":
                rh_Click(new View(this));
                break;
            case "鳄鱼":
                cro_Click(new View(this));
                break;
            case "乌龟":
                tor_Click(new View(this));
                break;
            case "狮子":
                lion_Click(new View(this));
                break;
            case "大象":
                ele_Click(new View(this));
                break;
            case "猴子":
                mon_Click(new View(this));
                break;
            case "松鼠":
                squ_Click(new View(this));
                break;
            case "兔子":
                rab_Click(new View(this));
                break;
            case "长颈鹿":
                gir_Click(new View(this));
                break;
            case "骆驼":
                cam_Click(new View(this));
                break;
            case "斑马":
                zrb_Click(new View(this));
                break;
            case "熊猫":
                pan_Click(new View(this));
                break;
            case "鹦鹉":
                par_Click(new View(this));
                break;
            case "狐狸":
                fox_Click(new View(this));
                break;
            case "河马":
                hip_Click(new View(this));
                break;
            case "孔雀":
                pea_Click(new View(this));
                break;
            case "驴":
                ass_Click(new View(this));
                break;
            case "羊":
                she_Click(new View(this));
                break;
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
//    public void setButtonClick(){
//        left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //refresh();
//            }
//        });
//        mid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(FirstSearchActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(FirstSearchActivity.this,VersionActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
    public void setButtonClick(){
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_search:
                        refresh();
                        break;
                    case R.id.btn_map:
                        Intent intent=new Intent(FirstSearchActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_version:
                        Intent intentversion=new Intent(FirstSearchActivity.this,VersionActivity.class);
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


    //刷新界面
    public void refresh(){
        finish();
        Intent intent=new Intent(FirstSearchActivity.this,FirstSearchActivity.class);
        startActivity(intent);
        finish();
    }
}
