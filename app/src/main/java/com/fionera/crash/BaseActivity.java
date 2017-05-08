package com.fionera.crash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity
 * Created by fionera on 17-3-10 in android_project.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected  Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}