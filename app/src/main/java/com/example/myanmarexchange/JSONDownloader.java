package com.example.myanmarexchange;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONDownloader {
Activity activity;

    public static String download(String url){
        StringBuffer result=new StringBuffer();
        try
        {
            URL url1=new URL(url);
            HttpURLConnection httpConn = ((HttpURLConnection)url1.openConnection());
            httpConn.setConnectTimeout(7000);
          //  httpConn.setReadTimeout(7000);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
                result.append(line).append("\n");

            reader.close();
            httpConn.disconnect();
            return result.toString();
        }
        catch (IOException e)
        {

            e.printStackTrace();
            return "false";
        }


    }
    public static String downloadTest(String url,Context context){
        StringBuffer result=new StringBuffer();
        try
        {
            URL url1=new URL(url);
            HttpURLConnection httpConn = ((HttpURLConnection)new URL(url).openConnection());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
                result.append(line).append("\n");

            reader.close();
            httpConn.disconnect();
            return result.toString();
        }
        catch (IOException e)
        {
          //  Activity acttvity = null;
            Toast.makeText(context,"jjjj",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return "false";
        }


    }
}
