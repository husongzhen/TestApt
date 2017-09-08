package com.allen.code.bindview;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.allen.code.bindview_api.ProxyTool;
import com.example.BindView;
import com.example.OnClick;
import com.example.TimeLog;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.hello)
    View hello;


    @BindView(R.id.text)
    View text;
    @TimeLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProxyTool.bind(this);
    }

    @TimeLog
    @OnClick({R.id.hello, R.id.text})
    public void onClick(View view) {
        Toast.makeText(this, "hello world" + "name == " + getName(view.getId()) + " name = " + view.getAccessibilityClassName().toString(), Toast.LENGTH_SHORT).show();
    }


    public String getName(int id) {
        Resources res = getResources();
        return res.getResourceEntryName(id);//得到的是 name
//return res.getResourceName(id);//得到的是 包/type/name
    }
}
