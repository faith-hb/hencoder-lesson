package com.hencoder.hotfix;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class MainActivity extends AppCompatActivity {

    TextView titleTv;
    Button showTitleBt;
    Button hotfixBt;
    Button removeHotfixBt;
    Button killSelfBt;

    File apk;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        System.out.println("attachBaseContext...");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate...");

        titleTv = findViewById(R.id.titleTv);
        showTitleBt = findViewById(R.id.showTitleBt);
        hotfixBt = findViewById(R.id.hotfixBt);
        removeHotfixBt = findViewById(R.id.removeHotfixBt);
        killSelfBt = findViewById(R.id.killSelfBt);

        // getCacheDir() = /data/user/0/com.hencoder.hotfix/cache
        apk = new File(getCacheDir() + "/32_hotfix-debug.apk");
//        apk = new File("/data/data/com.hencoder.hotfix/cache/32_hotfix-debug.apk");
        // 上面两个apk文件目录都可以，本项目采用第一个

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.showTitleBt:
                        Title title = new Title();
                        titleTv.setText(title.getTitle());
                        break;
                    case R.id.hotfixBt:
                        try (Source source = Okio.source(getAssets().open("apk/32_hotfix-debug.apk"));
                             BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
                            sink.writeAll(source);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.removeHotfixBt:
                        if(apk.exists()){
                            apk.delete();
                        }
                        break;
                    case R.id.killSelfBt:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        break;
                    default:
                        break;
                }
            }
        };

        showTitleBt.setOnClickListener(onClickListener);
        hotfixBt.setOnClickListener(onClickListener);
        removeHotfixBt.setOnClickListener(onClickListener);
        killSelfBt.setOnClickListener(onClickListener);
    }
}
