/*
 * Copyright 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2raw;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Activity displaying a fragment that implements RAW photo captures.
 */
public class CameraActivity extends Activity {

    private final static String TAG = "CameraActivity";

    private Button btnSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camera);
        setupViews();
    }



    private void setupViews(){
        Button btnSave = (Button)this.findViewById(R.id.save_pic);
           btnSave.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    new DelThread().start();
               }
           });
    }


    static class DelThread extends Thread {
        public void run() {
            String pic_path = Environment.getExternalStorageDirectory().getPath() + File.pathSeparatorChar + 'DIM';
            try {
                File f = new File(pic_path);
                if (f.isDirectory()) {
                    String[] file_list = f.list();
                    if (file_list != null) {
                        for (String one_file_path : file_list) {
                            new File(one_file_path).delete();
                        }
                    }
                }
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
           // Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式


           // ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// 将图片显示在ImageView里
        }
    }
}
