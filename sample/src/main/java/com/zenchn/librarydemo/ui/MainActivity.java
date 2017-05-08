package com.zenchn.librarydemo.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.zenchn.librarydemo.R;
import com.zenchn.librarydemo.base.BaseActivity;

import butterknife.BindView;

/**
 * 作    者：wangr on 2017/4/25 15:43
 * 描    述：
 * 修订记录：
 */

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.lv_demo)
    ListView lvDemo;
    private String[] _animationList = {"fade", "flipHorizontal", "flipVertical", "disappearTopLeft", "appearTopLeft", "appearBottomRight", "disappearBottomRight", "unzoom"};


    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, _animationList);
        lvDemo.setAdapter(adapter);
        lvDemo.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

}
