package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:15:06
 *@Description:${DESCRIPTION}数据库
 * */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDao {

    private final MyHelper myHelper;
    private SQLiteDatabase writableDatabase;

    public MyDao(Context context) {
        myHelper = new MyHelper(context, "mounth.db", null, 1);
    }

    public void addbanner(List<StudentBean.BannerdataBean> bannerdata) {
        writableDatabase = myHelper.getWritableDatabase();
        for (StudentBean.BannerdataBean bannerdatum : bannerdata) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("banner", bannerdatum.getImageUrl());
            writableDatabase.insert("banner", null, contentValues);
        }
        writableDatabase.close();
    }

    public void addnews(List<StudentBean.ListdataBean> bannerdata) {
        writableDatabase = myHelper.getWritableDatabase();
        for (StudentBean.ListdataBean bannerdatum : bannerdata) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", bannerdatum.getName());
            contentValues.put("info", bannerdatum.getInfo());
            contentValues.put("url", bannerdatum.getUrl());
            writableDatabase.insert("news", null, contentValues);
        }
        writableDatabase.close();
    }

    public List<StudentBean.BannerdataBean> querebanner() {
        SQLiteDatabase writableDatabase = myHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("banner", null, null, null, null, null, null);
        ArrayList<StudentBean.BannerdataBean> list = new ArrayList<>();
        if (cursor.moveToNext()) {
            String banner = cursor.getString(cursor.getColumnIndex("banner"));
            StudentBean.BannerdataBean bannerdataBean = new StudentBean.BannerdataBean();
            bannerdataBean.setImageUrl(banner);
            list.add(bannerdataBean);
        }
        return list;
    }

    public List<StudentBean.ListdataBean> querenews() {
        SQLiteDatabase writableDatabase = myHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("news", null, null, null, null, null, null);
        ArrayList<StudentBean.ListdataBean> list = new ArrayList<>();
        if (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String info = cursor.getString(cursor.getColumnIndex("info"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            StudentBean.ListdataBean bannerdataBean = new StudentBean.ListdataBean();
            bannerdataBean.setName(name);
            bannerdataBean.setInfo(info);
            bannerdataBean.setUrl(url);
            list.add(bannerdataBean);
        }
        return list;
    }


}
