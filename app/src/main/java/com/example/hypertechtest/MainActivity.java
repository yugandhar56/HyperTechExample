package com.example.hypertechtest;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.hypertechtest.ui.CustomRecyclerView;
import com.example.hypertechtest.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    CustomRecyclerView customRecyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        customRecyclerView =findViewById(R.id.main_recyclerView);
        setSupportActionBar(toolbar);
        List<MyModel> modelList=getData();
        MyVideosAdapter mAdapter = new MyVideosAdapter(modelList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        customRecyclerView.setLayoutManager(mLayoutManager);
        customRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //todo before setAdapter
        customRecyclerView.setActivity(this);

        //optional - to play only first visible video
        customRecyclerView.setPlayOnlyFirstVideo(true); // false by default

        //optional - by default we check if url ends with ".mp4". If your urls do not end with mp4, you can set this param to false and implement your own check to see if video points to url
        customRecyclerView.setCheckForMp4(false); //true by default

        //optional - download videos to local storage (requires "android.permission.WRITE_EXTERNAL_STORAGE" in manifest or ask in runtime)
        customRecyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo"); // (Environment.getExternalStorageDirectory() + "/Video") by default

        customRecyclerView.setDownloadVideos(true); // false by default

        customRecyclerView.setVisiblePercent(50); // percentage of View that needs to be visible to start playing

        //extra - start downloading all videos in background before loading RecyclerView
        List<String> urls = new ArrayList<>();
        for (MyModel object : modelList) {
            if (object.getVideo_url() != null && object.getVideo_url().contains("http"))
                urls.add(object.getVideo_url());
        }
        customRecyclerView.preDownload(urls);

        customRecyclerView.setAdapter(mAdapter);
        //call this functions when u want to start autoplay on loading async lists (eg firebase)
        customRecyclerView.smoothScrollBy(0,1);
        customRecyclerView.smoothScrollBy(0,-1);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //add this code to pause videos (when app is minimised or paused)
        customRecyclerView.stopVideos();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<MyModel> getData()
    {
        List<MyModel> modelList = new ArrayList<>();

        modelList.add(new MyModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
                "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg",
                "video1"));
        modelList.add(new MyModel("https://media.giphy.com/media/OWxrxRHY6afRu/giphy.gif","Gif1"));

//        modelList.add(new MyModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video2"));

        modelList.add(new MyModel("https://media1.giphy.com/media/3o7aCRZYNerX4ovPwI/giphy.gif?cid=790b761%E2%80%A6&rid=giphy.gif", "gif2"));

        modelList.add(new MyModel("text 2"));

        modelList.add(new MyModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video4"));

//        modelList.add(new MyModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "video4"));

        modelList.add(new MyModel("text 4"));
//        modelList.add(new MyModel( "https://media.giphy.com/media/1iTHHR8KaiDyhkHe/giphy_s.gif", "gif4"));

        modelList.add(new MyModel("text 5"));

        modelList.add(new MyModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video6"));

        modelList.add(new MyModel("https://media2.giphy.com/media/12qHWnTUBzLWXS/giphy.gif?cid=790b76119f69ba0a55b31dae09b2360c016f7f3d38ebfb0e&amp;rid=giphy.gif", "gif5"));

        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image8"));



        modelList.add(new MyModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video7"));

       /* modelList.add(new MyModel("https://media2.giphy.com/media/12qHWnTUBzLWXS/giphy.gif?cid=790b76119f69ba0a55b31dae09b2360c016f7f3d38ebfb0e&amp;rid=giphy.gif", "gif5"));

        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image8"));*/

//        modelList.add(new MyModel("text 6"));
//        modelList.add(new MyModel("text 7"));
//        modelList.add(new MyModel("text 8"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "image13"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "image14"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "video16"));
//        modelList.add(new MyModel("text 9"));
//        modelList.add(new MyModel("text 10"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image15"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "video17"));
//        modelList.add(new MyModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video18"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "image19"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "image20"));
//        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image21"));
        return modelList;
    }
}
