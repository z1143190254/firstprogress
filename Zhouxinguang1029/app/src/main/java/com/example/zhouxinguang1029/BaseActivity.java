package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:14:02
 *@Description:${DESCRIPTION}封装BaseActivity类
 * */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inisid());
        inisview();
        inisData();
    }

    protected abstract void inisData();

    protected abstract void inisview();

    protected abstract int inisid();
}
