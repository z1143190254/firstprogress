package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:14:08
 *@Description:${DESCRIPTION}做数据库和多条目
 * */

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeFtagment extends BaseFragment {

    private XListView xListView;
    private int pager = 1;
    private boolean isloadMore;
    private ArrayList<StudentBean.ListdataBean> list = new ArrayList<>();
    private MyDao myDao;


    @Override
    protected void inisview(View inflate) {
        xListView = inflate.findViewById(R.id.xlist);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = list.get(position).getUrl();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("key", url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int inisid() {
        return R.layout.homefragment;
    }

    @Override
    protected void inisData() {
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                isloadMore = false;
                getData();
            }

            @Override
            public void onLoadMore() {
                pager++;
                isloadMore = true;
                getData();
            }
        });
        myDao = new MyDao(getActivity());
        if (Until.getInstance().hasNet(getActivity())) {
            getData();
        } else {
            List<StudentBean.ListdataBean> querenews = myDao.querenews();
            Log.i("xxx", "查询成功" + querenews);
            MyBase myBase = new MyBase(list);
            xListView.setAdapter(myBase);
        }
    }

    private void getData() {
        String url = "";
        if (pager == 1) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer.json";
        } else if (pager == 2) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
        } else if (pager == 3) {
            url = "http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
        }
        Until.getInstance().doGet(url, new Until.MycallBack() {
            @Override
            public void ondoGetsuccess(String json) {
                final StudentBean studentBean = new Gson().fromJson(json, StudentBean.class);
                final List<StudentBean.BannerdataBean> bannerdata = studentBean.getBannerdata();
                List<StudentBean.ListdataBean> newlist = studentBean.getListdata();
                myDao.addnews(newlist);
                if (isloadMore) {
                    xListView.stopRefresh();
                    list.addAll(newlist);
                } else {
                    xListView.stopLoadMore();
                    list.clear();
                    list.addAll(newlist);
                }
                MyBase myBase = new MyBase(list);
                xListView.setAdapter(myBase);
            }

            @Override
            public void ondoGetphotosuccess(Bitmap bitmap) {

            }
        });


    }
}
