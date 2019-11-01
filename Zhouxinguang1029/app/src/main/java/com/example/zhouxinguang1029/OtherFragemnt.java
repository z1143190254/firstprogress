package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:14:06
 *@Description:${DESCRIPTION}
 * */

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OtherFragemnt extends BaseFragment {

    private TextView textView;

    @Override
    protected void inisview(View inflate) {
        textView = inflate.findViewById(R.id.text1);
    }

    @Override
    protected int inisid() {
        return R.layout.otherfragment;
    }

    //传值
    @Override
    protected void inisData() {
        Bundle bundle = getArguments();
        String key = bundle.getString("key");
        textView.setText(key);
    }
}
