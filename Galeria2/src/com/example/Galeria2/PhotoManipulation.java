package com.example.Galeria2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Asia on 14.05.2015.
 */
public class PhotoManipulation extends Activity{

    Bitmap photo;
    File photoFile;
    Intent intent;
    String ExternalStorageDirectoryPath, targetPath;
    static  final int REQUEST_TAKE_PHOTO=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photomanipulation);

        intent = getIntent();
        photo= (Bitmap) intent.getParcelableExtra("BitmapImage");

        ImageView pictureTaken = (ImageView) findViewById(R.id.photoView);

        pictureTaken.setImageBitmap(photo);

        Button save = (Button) findViewById(R.id.save);
        Button delete = (Button) findViewById(R.id.delete);
    }


    public void onClick(View view) {

        if (view.getId() == R.id.save) {
            SaveImage(photo);
        }

        finish();

        if (view.getId() == R.id.delete) {
            finish();
        }
    }


    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/DCIM/Test");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String timeStamp = new SimpleDateFormat ("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Image-"+ timeStamp +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
