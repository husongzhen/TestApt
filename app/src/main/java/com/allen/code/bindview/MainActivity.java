package com.allen.code.bindview;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.allen.code.bindview.switchBuk.ICaseAction;
import com.allen.code.bindview.switchBuk.SwichCase;
import com.example.LayoutId;
import com.example.OnClick;

import static com.allen.code.bindview.MainActivity$$Layout.hello;
import static com.allen.code.bindview.MainActivity$$Layout.text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    @BindView(R.id.hello)
//    View hello;
//    @BindView(R.id.text)
//    View text;


    @LayoutId(value = "activity_main", path = "app/src/main/res/layout/")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ProxyTool.bind(this);


        MainActivity$$Layout mainActivity$$Layout = new MainActivity$$Layout();
        mainActivity$$Layout.inject(this);
//        MainActivity$$Layout.hello.setOnClickListener(this);
//        MainActivity$$Layout.text.setOnClickListener(this);

        text.setOnClickListener(this);

        hello.setOnClickListener(this);


    }

    @OnClick({R.id.hello, R.id.text})
    public void onClick(final View view) {
        SwichCase.create()
                .scase(R.id.hello, new ICaseAction() {
                    @Override
                    public void onAction() {
                        helloMethod(view);
                    }
                })
                .scase(R.id.text, new ICaseAction() {
                    @Override
                    public void onAction() {
                        textMethod(view);
                    }
                })
                .action(view.getId());
    }

    private void textMethod(View view) {
        showToast(view);
    }

    private String getNameById(View view) {
        return getName(view.getId());
    }

    private void helloMethod(View view) {
        showToast(view);
    }

    private void showToast(View view) {
        Toast.makeText(this, "hello world" + "name == " + getNameById(view), Toast.LENGTH_SHORT).show();
    }


    public String getName(int id) {
        Resources res = getResources();
        return res.getResourceName(id);
    }
}
