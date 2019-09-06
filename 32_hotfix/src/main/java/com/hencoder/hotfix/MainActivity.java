package com.hencoder.hotfix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class MainActivity extends AppCompatActivity {

    TextView titleTv;
    Button showTitleBt;
    Button hotfixBt;

    File apk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTv = findViewById(R.id.titleTv);
        showTitleBt = findViewById(R.id.showTitleBt);
        hotfixBt = findViewById(R.id.hotfixBt);

        // /data/user/0/com.hencoder.hotfix/cache/32_hotfix-debug.apk
        apk = new File(getCacheDir() + "/32_hotfix-debug.apk");  // 由于getCacheDir()拿到的文件目录是上面那个，但实际文件目录是当前行这个，所以此处把文件目录写死，确保能正确读取文件
//        apk = new File("/data/data/com.hencoder.hotfix/cache" + "/32_hotfix-debug.apk");

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

//                        try (Source source = Okio.source(getAssets().open("apk/hotfix.dex"));
//                             BufferedSink sink = Okio.buffer(Okio.sink(apk))){
//                            sink.writeAll(source);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            ClassLoader classLoader = getClassLoader();
//                            Class loaderClass = BaseDexClassLoader.class;
//                            Field pathListField = loaderClass.getDeclaredField("pathList");
//                            pathListField.setAccessible(true);
//
//                            Object pathListObject = pathListField.get(classLoader);
//                            Class pathListClass = pathListObject.getClass();
//
//                            Field dexElementsField = pathListClass.getDeclaredField("dexElements");
//                            dexElementsField.setAccessible(true);
//
//                            Object dexElementsObject = dexElementsField.get(pathListObject);
//
//                            PathClassLoader newClassLoader = new PathClassLoader(apk.getPath(), null);
//                            Object newPathListObject = pathListField.get(newClassLoader);
//                            Object newDexElementsObject = dexElementsField.get(newPathListObject);
//
////                            int oldLength = Array.getLength(dexElementsObject);
//
//                            dexElementsField.set(pathListObject, newDexElementsObject);
//
//
//                        } catch (NoSuchFieldException e) {
//                            e.printStackTrace();
//                        } catch (IllegalArgumentException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }

//                        try {
//                            ClassLoader classLoader = getClassLoader();
//                            Class loaderClass = BaseDexClassLoader.class;
//                            Field pathListField = loaderClass.getDeclaredField("pathList");
//                            pathListField.setAccessible(true);
//                            Object pathListObject = pathListField.get(classLoader); // getClassLoader().pathList
//                            System.out.println("pathListObject：" + pathListObject);
//                            Class pathListClass = pathListObject.getClass();
//                            Field dexElementsField = pathListClass.getDeclaredField("dexElements");
//                            dexElementsField.setAccessible(true);
//                            Object dexElementsObject = dexElementsField.get(pathListObject); // getClassLoader().pathList.dexElements
//
//                            // classLoader.pathList.dexElements = ???;
////                            System.out.println("apk path：" + apk.getPath());
//                            PathClassLoader newClassLoader = new PathClassLoader(apk.getPath(), null);
//                            Object newPathListObject = pathListField.get(newClassLoader); // newClassLoader.pathList
//                            System.out.println("newPathListObject：" + newPathListObject);
//                            Object newDexElementsObject = dexElementsField.get(newPathListObject); // newClassLoader.pathList.dexElements
//                            System.out.println("newDexElementsObject：" + newDexElementsObject);
//
//                            dexElementsField.set(pathListObject, newDexElementsObject);
//
////                            int oldLength = Array.getLength(dexElementsObject);
////                            int newLength = Array.getLength(newDexElementsObject);
////                            Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
////                            for (int i = 0; i < newLength; i++) {
////                                Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i));
////                            }
////                            for (int i = 0; i < oldLength; i++) {
////                                Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObject, i));
////                            }
////
////                            dexElementsField.set(pathListObject, concatDexElementsObject);
//                        } catch (NoSuchFieldException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }

                        break;
                    default:
                        break;
                }
            }
        };

        showTitleBt.setOnClickListener(onClickListener);
        hotfixBt.setOnClickListener(onClickListener);
    }
}
