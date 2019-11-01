package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:13:52
 *@Description:${DESCRIPTION}网络状态
 * */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Until {
    static Until until = new Until();

    public static Until getInstance() {
        return until;
    }

    public Until() {
    }

    public String io2String(InputStream inputStream) {
        String json = "";
        try {
            int len = -1;
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
            json = new String(bytes1);
        } catch (IOException e) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }

    public Bitmap bitmap(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    public void doGet(final String httpurl, final MycallBack mycallBack) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String json = "";
                InputStream inputStream = null;
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(httpurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        json = io2String(inputStream);
                    } else {
                        Log.i("xxx", "加载失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }

                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                mycallBack.ondoGetsuccess(s);
            }
        }.execute();
    }

    public void doGetphoto(final String httpurlphoto, final MycallBack mycallBack) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap bitmap = null;
                InputStream inputStream = null;
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(httpurlphoto);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        bitmap = bitmap(inputStream);
                    } else {
                        Log.i("xxx", "加载图片失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                mycallBack.ondoGetphotosuccess(bitmap);
            }
        }.execute();
    }

    public boolean hasNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean iswifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ismoble(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }

    public interface MycallBack {
        void ondoGetsuccess(String json);

        void ondoGetphotosuccess(Bitmap bitmap);

    }

}
