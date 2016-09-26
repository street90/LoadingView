package com.song.loadingview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.song.loading.LineLoading;

public class MainActivity extends AppCompatActivity {

    LineLoading ll;
    float loading = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LineLoading) findViewById(R.id.id3);

        ll.setComplete(0.5f);

    }

    public void btck(View view)
    {
        loading += 0.1;
        ll.setComplete(loading);
    }

}
