package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:14:03
 *@Description:${DESCRIPTION}封装BaseFragment方法
 * */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(inisid(), container, false);
        inisview(inflate);
        return inflate;
    }

    protected abstract void inisview(View inflate);

    protected abstract int inisid();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inisData();
    }

    protected abstract void inisData();
}
