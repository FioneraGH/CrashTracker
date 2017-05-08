package com.fionera.crash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fionera.ctracker.util.DefaultCrashHandler;

/**
 * MainActivity
 * Created by fionera on 17-5-8 in CrashTracker.
 */

public class MainActivity
        extends BaseActivity {

    private Button btnCreateCrash;
    private SeekBar sbProgressStep;
    private TextView tvProgressStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnCreateCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        throw new RuntimeException("Exception by us");
                    }
                }).start();
            }
        });

        sbProgressStep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgressStep.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /*
                here to occur exception
                 */
                findViewById(R.id.tv_crash_info).setSelected(true);
            }
        });
    }

    private void initView() {
        btnCreateCrash = (Button) findViewById(R.id.btn_create_crash);
        sbProgressStep = (SeekBar) findViewById(R.id.sb_progress_step);
        tvProgressStep = (TextView) findViewById(R.id.tv_progress_step);
    }
}
