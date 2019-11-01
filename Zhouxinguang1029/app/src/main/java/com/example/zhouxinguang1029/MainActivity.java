package com.example.zhouxinguang1029;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<String> titlelist = new ArrayList<>();
    private ImageView imageView;

    @Override
    protected void inisData() {
        titlelist.add("最新");
        titlelist.add("婆媳");
        titlelist.add("房产");
        titlelist.add("仲裁");
        titlelist.add("北京");

        HomeFtagment homeFtagment = new HomeFtagment();
        list.add(homeFtagment);

        OtherFragemnt otherFragemnt = new OtherFragemnt();
        Bundle bundle = new Bundle();
        bundle.putString("key", titlelist.get(1));
        otherFragemnt.setArguments(bundle);
        list.add(otherFragemnt);

        OtherFragemnt otherFragemnt1 = new OtherFragemnt();
        Bundle bundle1 = new Bundle();
        bundle1.putString("key", titlelist.get(2));
        otherFragemnt1.setArguments(bundle1);
        list.add(otherFragemnt1);

        OtherFragemnt otherFragemnt2 = new OtherFragemnt();
        Bundle bundle2 = new Bundle();
        bundle2.putString("key", titlelist.get(3));
        otherFragemnt2.setArguments(bundle2);
        list.add(otherFragemnt2);

        OtherFragemnt otherFragemnt3 = new OtherFragemnt();
        Bundle bundle3 = new Bundle();
        bundle3.putString("key", titlelist.get(4));
        otherFragemnt3.setArguments(bundle3);
        list.add(otherFragemnt3);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titlelist.get(position);
            }
        });
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void inisview() {
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
        imageView = findViewById(R.id.image11);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri data1 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected int inisid() {
        return R.layout.activity_main;
    }
}
