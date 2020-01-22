package com.intechdev.tcommerce.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class WebViewFragment extends AsyncTask<String,Void,Void> {

    private Context context;
    public void setContext(Context context){
        this.context = context;
    }
    @Override
    protected Void doInBackground(String... strings) {
        String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
        String fileName = "tcommerce.apk";
        String wholePath = PATH + fileName;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file,fileName);
            if(outputFile.exists()){
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while((len1 = is.read(buffer)) != -1){
                fos.write(buffer,0,len1);
            }
            fos.close();
            is.close();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(wholePath)),"application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
