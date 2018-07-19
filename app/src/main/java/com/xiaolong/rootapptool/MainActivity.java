package com.xiaolong.rootapptool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.xiaolong.rootapptool.utils.MockLocation;
import com.xiaolong.rootapptool.utils.Shell;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Shell.getInstance().su();
//        MockLocation.getInstance().setMockLocation();
    }
}
