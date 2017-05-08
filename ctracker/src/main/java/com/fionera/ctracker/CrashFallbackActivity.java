package com.fionera.ctracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CrashFallbackActivity
        extends AppCompatActivity {

    private Button btnCrashFinish;
    private TextView tvCrashInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_fallback);
        initView();

        btnCrashFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCrashInfo.setText(getIntent().getStringExtra("crash_info"));
    }

    private void initView() {
        btnCrashFinish = (Button) findViewById(R.id.btn_crash_finish);
        tvCrashInfo = (TextView) findViewById(R.id.tv_crash_info);
    }
}
