package com.example.zhouxinguang1029;
/*
 *@auther:周鑫光
 *@Date: 2019/10/29
 *@Time:14:35
 *@Description:${DESCRIPTION}多条目适配器
 * */

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.List;

public class MyBase extends BaseAdapter {
    private List<StudentBean.ListdataBean> list;
    private int Type0 = 0;
    private int Type1 = 1;

    public MyBase(List<StudentBean.ListdataBean> list) {

        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        if (type == 0) {
            return Type0;
        } else if (type == 1) {
            return Type1;
        }
        return Type0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            if (itemViewType == Type0) {
                convertView = View.inflate(parent.getContext(), R.layout.xiangyou, null);
                holder.imageView = convertView.findViewById(R.id.image1);
                holder.info = convertView.findViewById(R.id.tv_name);
                holder.title = convertView.findViewById(R.id.tv_title1);
                holder.shijian = convertView.findViewById(R.id.tv_shijian);
                holder.name = convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            } else if (itemViewType == Type1) {
                convertView = View.inflate(parent.getContext(), R.layout.xiangzuo, null);
                holder.imageView = convertView.findViewById(R.id.image1);
                holder.info = convertView.findViewById(R.id.tv_name);
                holder.name = convertView.findViewById(R.id.tv_title);
                holder.shijian = convertView.findViewById(R.id.tv_shijian);
                holder.title = convertView.findViewById(R.id.tv_title1);
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        StudentBean.ListdataBean listdataBean = list.get(position);
        holder.name.setText(listdataBean.getName());
        holder.info.setText(listdataBean.getInfo());
        holder.title.setText(listdataBean.getContent());
        holder.shijian.setText(listdataBean.getPublishedAt());
        final ViewHolder finalHolder = holder;
        Until.getInstance().doGetphoto(listdataBean.getAvatar(), new Until.MycallBack() {
            @Override
            public void ondoGetsuccess(String json) {

            }

            @Override
            public void ondoGetphotosuccess(Bitmap bitmap) {
                finalHolder.imageView.setImageBitmap(bitmap);
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView name;
        TextView info;
        TextView title;
        TextView shijian;


    }
}
