package com.gaurav.june2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recycler_view);

        String url = "https://cdn.vox-cdn.com/thumbor/iS9cu8-U-hh8rUGhUnc4ulkJ1wE=/0x0:2040x1360/1820x1213/filters:focal(860x1034:1186x1360)/cdn.vox-cdn.com/uploads/chorus_image/image/59377089/wjoel_180413_1777_android_001.1523625143.jpg";

        String[] urls = {url, url, url, url, url, url, url, url, url, url, url, url, url, url, url, url, url, url};

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Dashboard.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        MyAdapter adapter = new MyAdapter(urls, Dashboard.this);
        recyclerView.setAdapter(adapter);

    }
    
}