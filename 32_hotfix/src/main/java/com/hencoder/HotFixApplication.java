package com.hencoder;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;

public class HotFixApplication extends Application {

    File apk;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        apk = new File(getCacheDir() + "/32_hotfix-debug.apk");
//        apk = new File("/data/data/com.hencoder.hotfix/cache/32_hotfix-debug.apk");
        if (apk.exists()) {
            try {
                ClassLoader classLoader = getClassLoader();
                Class loaderClass = BaseDexClassLoader.class;
                Field pathListField = loaderClass.getDeclaredField("pathList");
                pathListField.setAccessible(true);
                Object pathListObject = pathListField.get(classLoader); // getClassLoader().pathList
                System.out.println("pathListObject：" + pathListObject);
                Class pathListClass = pathListObject.getClass();
                Field dexElementsField = pathListClass.getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);
                Object dexElementsObject = dexElementsField.get(pathListObject); // getClassLoader().pathList.dexElements

                // classLoader.pathList.dexElements = ???;
//                            System.out.println("apk path：" + apk.getPath());
                PathClassLoader newClassLoader = new PathClassLoader(apk.getPath(), null);
                Object newPathListObject = pathListField.get(newClassLoader); // newClassLoader.pathList
                System.out.println("newPathListObject：" + newPathListObject);
                Object newDexElementsObject = dexElementsField.get(newPathListObject); // newClassLoader.pathList.dexElements
                System.out.println("newDexElementsObject：" + newDexElementsObject);

                dexElementsField.set(pathListObject, newDexElementsObject);

//                            int oldLength = Array.getLength(dexElementsObject);
//                            int newLength = Array.getLength(newDexElementsObject);
//                            Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
//                            for (int i = 0; i < newLength; i++) {
//                                Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i));
//                            }
//                            for (int i = 0; i < oldLength; i++) {
//                                Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObject, i));
//                            }
//
//                            dexElementsField.set(pathListObject, concatDexElementsObject);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}