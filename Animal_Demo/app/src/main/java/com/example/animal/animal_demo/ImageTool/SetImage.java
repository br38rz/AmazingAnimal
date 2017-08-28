package com.example.animal.animal_demo.ImageTool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kevin on 2017/8/27.
 */

public class SetImage {
    public static void setImageToImageView(final ImageView imageView,final String imageUrl){
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.e("HHH","设置图片成功");
                super.handleMessage(msg);
                Bitmap bitmap=(Bitmap)msg.obj;
                imageView.setImageBitmap(bitmap);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(imageUrl);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    InputStream is=httpURLConnection.getInputStream();
                    Bitmap bitmap= BitmapFactory.decodeStream(is);
                    Message msg=new Message();
                    msg.obj=bitmap;
                    handler.sendMessage(msg);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
